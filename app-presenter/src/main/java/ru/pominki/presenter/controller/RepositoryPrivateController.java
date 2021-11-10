package ru.pominki.presenter.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.pominki.presenter.dto.RepoDto;
import ru.pominki.presenter.entity.Repository;
import ru.pominki.presenter.entity.User;
import ru.pominki.presenter.exception.NotFoundException;
import ru.pominki.presenter.exception.UserNotFoundExeption;
import ru.pominki.presenter.model.KafkaMsg;
import ru.pominki.presenter.payload.CreateRepoPayload;
import ru.pominki.presenter.repository.RepositoryRepository;
import ru.pominki.presenter.service.ClassCastToDto;
import ru.pominki.presenter.service.producer.BranchProducerService;
import ru.pominki.presenter.service.producer.ProducerService;
import ru.pominki.presenter.service.Storage.FilesUploader;
import ru.pominki.presenter.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/private/repos")
public class RepositoryPrivateController {
    @Autowired
    ProducerService producerService;

    @Autowired
    UserService userService;

    @Autowired
    ClassCastToDto classCastToDto;

    @Autowired
    FilesUploader filesUploader;

    @Autowired
    RepositoryRepository repositoryRepository;

    @Autowired
    BranchProducerService branchProducerService;

    private User getAuthentificatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByUsername(currentUserName).orElseThrow(
                () -> {throw new UserNotFoundExeption("");}
        );

        return currentUser;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create_repo(@RequestBody CreateRepoPayload createRepoPayload) {
        try {
            User u = getAuthentificatedUser();
            createRepoPayload.setOwner(u.getId());

            createRepoPayload.setFolderId(filesUploader.createRepositoryFolder(createRepoPayload.getName()));

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(createRepoPayload);
            System.out.println(json);
            producerService.produce_create_repo(new KafkaMsg(json));

            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRepo(@RequestParam Long repoId) {
        try {
            User u = getAuthentificatedUser();

            boolean f = false;
            for(Repository repo : u.getRepositories()) {
                if (repo.getId() == repoId) {
                    f = true;
                    break;
                }
            }

            if (!f) { throw new NotFoundException("Repo not found!"); }

//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            String json = ow.writeValueAsString(repoId);
//            System.out.println(json);
            String jsonString = new JSONObject()
                    .put("repoId", repoId.toString())
                    .toString();
            producerService.produceDeleteRepo(new KafkaMsg(jsonString));

            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/my_repos")
    public ResponseEntity<?> showMyRepos() {
        User u = getAuthentificatedUser();
        List<RepoDto> list = new ArrayList<>();
        for (Repository r : u.getRepositories()) {
            list.add(classCastToDto.convertRepoToRepoDto(r));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/check_name")
    public ResponseEntity<?> checkforName(@RequestParam String name) {
        return ResponseEntity.ok(!(repositoryRepository.countAllByName(name) > 0));
    }
}

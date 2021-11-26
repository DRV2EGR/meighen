package ru.meighgen.presenter.controller;

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
import ru.meighgen.presenter.dto.RepoDto;
import ru.meighgen.presenter.entity.Repository;
import ru.meighgen.presenter.exception.NotFoundException;
import ru.meighgen.presenter.exception.UserNotFoundExeption;
import ru.meighgen.presenter.service.ClassCastToDto;
import ru.meighgen.presenter.service.UserService;
import ru.meighgen.presenter.dto.RepoShortDto;
import ru.meighgen.presenter.entity.User;
import ru.meighgen.presenter.model.KafkaMsg;
import ru.meighgen.presenter.payload.CreateRepoPayload;
import ru.meighgen.presenter.repository.RepositoryRepository;
import ru.meighgen.presenter.service.producer.BranchProducerService;
import ru.meighgen.presenter.service.producer.ProducerService;
import ru.meighgen.presenter.service.Storage.FilesUploader;

/**
 * The type Repository private controller.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/private/repos")
public class RepositoryPrivateController {
    /**
     * The Producer service.
     */
    @Autowired
    ProducerService producerService;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The Class cast to dto.
     */
    @Autowired
    ClassCastToDto classCastToDto;

    /**
     * The Files uploader.
     */
    @Autowired
    FilesUploader filesUploader;

    /**
     * The Repository repository.
     */
    @Autowired
    RepositoryRepository repositoryRepository;

    /**
     * The Branch producer service.
     */
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

    /**
     * Create repo response entity.
     *
     * @param createRepoPayload the create repo payload
     * @return the response entity
     */
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

    /**
     * Delete repo response entity.
     *
     * @param repoId the repo id
     * @return the response entity
     */
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

    /**
     * Show my repos response entity.
     *
     * @return the response entity
     */
    @GetMapping("/my_repos")
    public ResponseEntity<?> showMyRepos() {
        User u = getAuthentificatedUser();
        List<RepoDto> list = new ArrayList<>();
        for (Repository r : u.getRepositories()) {
            list.add(classCastToDto.convertRepoToRepoDto(r));
        }
        return ResponseEntity.ok(list);
    }

    /**
     * Show my repos as strings response entity.
     *
     * @return the response entity
     */
    @GetMapping("/repos_short")
    public ResponseEntity<?> showMyReposAsStrings() {
        User u = getAuthentificatedUser();
        List<RepoShortDto> list = new ArrayList<>();
        for (Repository r : u.getRepositories()) {
            list.add(classCastToDto.convertRepositoryToRepoShortDto(r));
        }
        return ResponseEntity.ok(list);
    }

    /**
     * Checkfor name response entity.
     *
     * @param name the name
     * @return the response entity
     */
    @GetMapping("/check_name")
    public ResponseEntity<?> checkforName(@RequestParam String name) {
        return ResponseEntity.ok(!(repositoryRepository.countAllByName(name) > 0));
    }

    /**
     * Gets repo info.
     *
     * @param repoId the repo id
     * @return the repo info
     */
    @GetMapping("/repo")
    public ResponseEntity<?> getRepoInfo(@RequestParam Long repoId) {
        User u = getAuthentificatedUser();

        return ResponseEntity.ok(
                u.getRepositories().stream().filter(r -> r.getId() == repoId)
                        .map(repo -> classCastToDto.convertRepoToRepoDto(repo)).findFirst()
        );
    }

    @GetMapping("/my_collab_repos")
    public ResponseEntity<?> getCollabRepos() {
        User u = getAuthentificatedUser();

        List<Repository> clorep = repositoryRepository.findAllByCollaboratorsContains(u);

        return ResponseEntity.ok(
                clorep.stream().map(rep -> classCastToDto.convertRepoToRepoDto(rep)).toArray()
        );
    }
    
    @PostMapping("/add_collaborator")
    public ResponseEntity<?> addCollabToRepo(@RequestParam String email, @RequestParam Long repoId) {
        User u = getAuthentificatedUser();

        Repository r = repositoryRepository.findById(repoId).orElseThrow(() -> {throw new NotFoundException("Repo not found");});
        r.getCollaborators().add(userService.findUserByEmail(email));
        repositoryRepository.save(r);

        return ResponseEntity.ok("OK");
    }
}

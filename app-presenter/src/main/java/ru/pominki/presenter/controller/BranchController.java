package ru.pominki.presenter.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.pominki.presenter.dto.BranchDto;
import ru.pominki.presenter.dto.CommitDto;
import ru.pominki.presenter.entity.Branch;
import ru.pominki.presenter.entity.Repository;
import ru.pominki.presenter.entity.User;
import ru.pominki.presenter.exception.UserNotFoundExeption;
import ru.pominki.presenter.model.CreateBranchModel;
import ru.pominki.presenter.model.DeleteBranchModel;
import ru.pominki.presenter.model.KafkaMsg;
import ru.pominki.presenter.payload.CreateBranchPayload;
import ru.pominki.presenter.repository.BranchRepository;
import ru.pominki.presenter.repository.RepositoryRepository;
import ru.pominki.presenter.service.ClassCastToDto;
import ru.pominki.presenter.service.UserService;
import ru.pominki.presenter.service.producer.BranchProducerService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/private/branch")
public class BranchController {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    UserService userService;

    @Autowired
    ClassCastToDto classCastToDto;

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

    @GetMapping("/commits")
    public ResponseEntity<?> getCommits(@RequestParam Long branchId) {
        User u = getAuthentificatedUser();
        Branch b = branchRepository.findAllByCreator(u.getId()).stream().filter(branch -> branch.getId() == branchId).findFirst().get();
        return ResponseEntity.ok(
                b.getCommits().stream().map(commit -> classCastToDto.convertCommitToCommitDto(commit)).collect(Collectors.toList())
        );
    }

    @GetMapping("/find")
    public ResponseEntity<?> getBranch(@RequestParam Long branchId) {
        User u = getAuthentificatedUser();
        return ResponseEntity.ok(
                branchRepository.findAllByCreator(u.getId()).stream().filter(branch -> branch.getId() == branchId).map(
                        branch -> classCastToDto.convertBranchToBranchDto(branch)
                ).findFirst().get()
        );
    }

    @GetMapping("/find/by-repository")
    public ResponseEntity<?> getBranchByRepo(@RequestParam Long repoId) {
        User u = getAuthentificatedUser();
        return ResponseEntity.ok(
                repositoryRepository.findById(repoId).get()
                        .getBranches().stream()
                   .map( branch -> classCastToDto.convertBranchToBranchDto(branch)
                ).collect(Collectors.toList())
            );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBranchByRepoId(@RequestBody CreateBranchPayload createBranchPayload) {
        User u = getAuthentificatedUser();

        CreateBranchModel createBranchModel = new CreateBranchModel(
                createBranchPayload.getName(),
                u.getId(),
                createBranchPayload.getRepoId()
        );

        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(createBranchModel);
            System.out.println(json);
            branchProducerService.createBranch(new KafkaMsg(json));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBranchByRepoId(@RequestParam Long branchId) {
        User u = getAuthentificatedUser();

        DeleteBranchModel deleteBranchModel = new DeleteBranchModel(branchId);
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(deleteBranchModel);
            System.out.println(json);
            branchProducerService.deleteBranch(new KafkaMsg(json));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("OK");
    }
}

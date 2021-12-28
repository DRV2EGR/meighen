package ru.meighgen.presenter.controller;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.meighgen.presenter.entity.Branch;
import ru.meighgen.presenter.exception.UserNotFoundExeption;
import ru.meighgen.presenter.service.ClassCastToDto;
import ru.meighgen.presenter.service.UserService;
import ru.meighgen.presenter.entity.User;
import ru.meighgen.presenter.model.CreateBranchModel;
import ru.meighgen.presenter.model.DeleteBranchModel;
import ru.meighgen.presenter.model.KafkaMsg;
import ru.meighgen.presenter.payload.CreateBranchPayload;
import ru.meighgen.presenter.repository.BranchRepository;
import ru.meighgen.presenter.repository.RepositoryRepository;
import ru.meighgen.presenter.service.producer.BranchProducerService;

/**
 * The type Branch controller.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/private/branch")
public class BranchController {
    /**
     * The Branch repository.
     */
    @Autowired
    BranchRepository branchRepository;

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
     * Gets commits.
     *
     * @param branchId the branch id
     * @return the commits
     */
    @GetMapping("/commits")
    public ResponseEntity<?> getCommits(@RequestParam Long branchId) {
        User u = getAuthentificatedUser();
        Branch b = branchRepository.findAllByCreator(u.getId()).stream().filter(branch -> branch.getId() == branchId).findFirst().get();
        return ResponseEntity.ok(
                b.getCommits().stream().map(commit -> classCastToDto.convertCommitToCommitDto(commit)).collect(Collectors.toList())
        );
    }

    /**
     * Gets branch.
     *
     * @param branchId the branch id
     * @return the branch
     */
    @GetMapping("/find")
    public ResponseEntity<?> getBranch(@RequestParam Long branchId) {
        User u = getAuthentificatedUser();
        return ResponseEntity.ok(
                branchRepository.findAllByCreator(u.getId()).stream().filter(branch -> branch.getId() == branchId).map(
                        branch -> classCastToDto.convertBranchToBranchDto(branch)
                ).findFirst().get()
        );
    }

    /**
     * Gets branch by repo.
     *
     * @param repoId the repo id
     * @return the branch by repo
     */
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

    /**
     * Create branch by repo id response entity.
     *
     * @param createBranchPayload the create branch payload
     * @return the response entity
     */
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

    /**
     * Delete branch by repo id response entity.
     *
     * @param branchId the branch id
     * @return the response entity
     */
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

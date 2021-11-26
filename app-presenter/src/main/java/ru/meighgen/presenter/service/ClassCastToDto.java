package ru.meighgen.presenter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.meighgen.presenter.dto.*;
import ru.meighgen.presenter.entity.Branch;
import ru.meighgen.presenter.entity.Commit;
import ru.meighgen.presenter.entity.Repository;
import ru.meighgen.presenter.entity.User;

/**
 * The type Class cast to dto.
 */
@Service
public class ClassCastToDto {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Convert repo to repo dto repo dto.
     *
     * @param repository the repository
     * @return the repo dto
     */
    public RepoDto convertRepoToRepoDto(Repository repository) {
        List<BranchDto> branchDtoList = new ArrayList<>();
        for (Branch b : repository.getBranches()) {
            branchDtoList.add(convertBranchToBranchDto(b));
        }
        RepoDto repoDto = new RepoDto(
                repository.getId(),
                repository.getName(),
                repository.getTimeOfRepoCreation(),
                repository.getDefaultBranch().getName(),
                branchDtoList,
                new ArrayList<>()
        );

        for (User u : repository.getCollaborators()) {
            repoDto.getCollaborators().add(userService.convertUserToUserDto(u));
        }

        return repoDto;
    }

    /**
     * Convert commit to commit dto commit dto.
     *
     * @param commit the commit
     * @return the commit dto
     */
    public CommitDto convertCommitToCommitDto(Commit commit) {
        CommitDto commitDto = new CommitDto(
                commit.getCommitId(), commit.getMessage(), null
        );
//        if (commit.getPreviouse() != null) { commitDto.setPreviouse(commit.getPreviouse().getCommitId()); }
        System.out.println(commit);
        if (commit.getNext() != null) { commitDto.setNext(commit.getNext().getCommitId()); }

        return commitDto;
    }

    /**
     * Convert branch to branch dto branch dto.
     *
     * @param branch the branch
     * @return the branch dto
     */
    public BranchDto convertBranchToBranchDto(Branch branch) {
        List<CommitDto> commitDtoList = new ArrayList<>();
        for (Commit c : branch.getCommits()) {
            commitDtoList.add(convertCommitToCommitDto(c));
        }
        BranchDto branch1 = new BranchDto(
                branch.getId(), branch.getCreator(), branch.getName(), branch.getTimeOfBranchCreation(), commitDtoList, null);
        if (branch.getHEAD() != null) { branch1.setHead(String.valueOf(branch.getHEAD())); }
        return branch1;
    }

    /**
     * Convert repository to repo short dto repo short dto.
     *
     * @param repository the repository
     * @return the repo short dto
     */
    public RepoShortDto convertRepositoryToRepoShortDto(Repository repository) {
        return new RepoShortDto(
                repository.getId(),
                repository.getName()
        );
    }

    /**
     * Convert user to user public dto user public dto.
     *
     * @param user the user
     * @return the user public dto
     */
    public UserPublicDto convertUserToUserPublicDto(User user) {
        return new UserPublicDto(
                user.getId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }
}

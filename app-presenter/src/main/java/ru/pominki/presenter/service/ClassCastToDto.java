package ru.pominki.presenter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pominki.presenter.dto.BranchDto;
import ru.pominki.presenter.dto.CommitDto;
import ru.pominki.presenter.dto.RepoDto;
import ru.pominki.presenter.entity.Branch;
import ru.pominki.presenter.entity.Commit;
import ru.pominki.presenter.entity.Repository;
import ru.pominki.presenter.entity.User;

@Service
public class ClassCastToDto {
    @Autowired
    UserService userService;

    public RepoDto convertRepoToRepoDto(Repository repository) {
        List<BranchDto> branchDtoList = new ArrayList<>();
        for (Branch b : repository.getBranches()) {
            branchDtoList.add(convertBranchToBranchDto(b));
        }
        RepoDto repoDto = new RepoDto(
                repository.getId(),
                repository.getName(),
                repository.getTimeOfRepoCreation(),
                repository.getHEAD(),
                branchDtoList,
                new ArrayList<>()
        );

        for (User u : repository.getCollaborators()) {
            repoDto.getCollaborators().add(userService.convertUserToUserDto(u));
        }

        return repoDto;
    }

    public CommitDto convertCommitToCommitDto(Commit commit) {
        CommitDto commitDto = new CommitDto(
                commit.getCommitId(), commit.getMessage(), null
        );
//        if (commit.getPreviouse() != null) { commitDto.setPreviouse(commit.getPreviouse().getCommitId()); }
        if (commit.getNext() != null) { commitDto.setNext(commit.getNext().getCommitId()); }

        return commitDto;
    }

    public BranchDto convertBranchToBranchDto(Branch branch) {
        List<CommitDto> commitDtoList = new ArrayList<>();
        for (Commit c : branch.getCommits()) {
            commitDtoList.add(convertCommitToCommitDto(c));
        }
        return new BranchDto(branch.getId(), branch.getCreator(), branch.getName(), branch.getTimeOfBranchCreation(), commitDtoList);
    }
}

package ru.pominki.commiter.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.entity.Branch;
import ru.pominki.commiter.entity.Commit;
import ru.pominki.commiter.repository.BranchRepository;
import ru.pominki.commiter.repository.CommitRepository;

@Service
public class CommitService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    CommitRepository commitRepository;

    public void createCommit(Long branchId, String message, String folderId, String commitId) {
        Branch branch = branchRepository.findBranchById(branchId);
        Commit commit = new Commit();
        commit.setMessage(message);
        commit.setFolderId(folderId);
        commit.setCommitId(commitId);
        commitRepository.save(commit);

        // Set next and prev
        if (branch.getCommits().isEmpty() || branch.getCommits().size() == 0) {
            branch.setHEAD(commit.getCommitId());
            branch.getCommits().add(commit);

            commitRepository.save(commit);
            branchRepository.save(branch);
        } else {
            Commit oldHead = commitRepository.findCommitByCommitId(branch.getHEAD());
//            commit.setPreviouse(oldHead);
            oldHead.setNext(commit);

            branch.setHEAD(commit.getCommitId());
            branch.getCommits().add(commit);

            commitRepository.save(oldHead);
            commitRepository.save(commit);
            branchRepository.save(branch);
        }
    }
}

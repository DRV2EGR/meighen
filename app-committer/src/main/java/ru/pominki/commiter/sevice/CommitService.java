package ru.pominki.commiter.sevice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.entity.Branch;
import ru.pominki.commiter.entity.Commit;
import ru.pominki.commiter.repository.BranchRepository;
import ru.pominki.commiter.repository.CommitRepository;
import ru.pominki.commiter.sevice.Storage.FilesUploader;

@Service
public class CommitService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    CommitRepository commitRepository;

    @Autowired
    FilesUploader filesUploader;

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

    public void copyFilesFromCommitToCommit(String oldCommitFolder, String newCommitFolder) throws IOException {
        filesUploader.copyCommit(oldCommitFolder, newCommitFolder);
    }

    public void uploadFilesToCommit(List<File> files, String commitFolderId) throws IOException {
        Tika tika = new Tika();
        for (File f : files) {
            filesUploader.upload(f, commitFolderId, tika.detect(f));
        }
    }
}

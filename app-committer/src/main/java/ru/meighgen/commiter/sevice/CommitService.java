package ru.meighgen.commiter.sevice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.meighgen.commiter.repository.CommitRepository;
import ru.meighgen.commiter.entity.Branch;
import ru.meighgen.commiter.entity.Commit;
import ru.meighgen.commiter.repository.BranchRepository;
import ru.meighgen.commiter.sevice.Storage.FilesUploader;

/**
 * The type Commit service.
 */
@Service
public class CommitService {
    /**
     * The Branch repository.
     */
    @Autowired
    BranchRepository branchRepository;

    /**
     * The Commit repository.
     */
    @Autowired
    CommitRepository commitRepository;

    /**
     * The Files uploader.
     */
    @Autowired
    FilesUploader filesUploader;

    /**
     * Create commit.
     *
     * @param branchId the branch id
     * @param message  the message
     * @param folderId the folder id
     * @param commitId the commit id
     */
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

    /**
     * Copy files from commit to commit.
     *
     * @param oldCommitFolder the old commit folder
     * @param newCommitFolder the new commit folder
     * @throws IOException the io exception
     */
    public void copyFilesFromCommitToCommit(String oldCommitFolder, String newCommitFolder) throws IOException {
        filesUploader.copyCommit(oldCommitFolder, newCommitFolder);
    }

    /**
     * Upload files to commit.
     *
     * @param files          the files
     * @param commitFolderId the commit folder id
     * @throws IOException the io exception
     */
    public void uploadFilesToCommit(List<File> files, String commitFolderId) throws IOException {
        Tika tika = new Tika();
        for (File f : files) {
            filesUploader.upload(f, commitFolderId, tika.detect(f));
        }
    }
}

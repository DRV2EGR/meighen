package ru.meighgen.commiter.sevice;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.meighgen.commiter.entity.Branch;
import ru.meighgen.commiter.entity.Repository;
import ru.meighgen.commiter.repository.BranchRepository;
import ru.meighgen.commiter.repository.RepositoryRepository;
import ru.meighgen.commiter.sevice.Storage.FilesUploader;

/**
 * The type Branch service.
 */
@Service
public class BranchService {
    /**
     * The Branch repository.
     */
    @Autowired
    BranchRepository branchRepository;

    /**
     * The Repository repository.
     */
    @Autowired
    RepositoryRepository repositoryRepository;

    /**
     * The Files uploader.
     */
    @Autowired
    FilesUploader filesUploader;

    /**
     * Create branch.
     *
     * @param name   the name
     * @param owner  the owner
     * @param repoId the repo id
     */
    public void createBranch(String name, Long owner, Long repoId) {
        try {

            Branch branch = new Branch();
            branch.setName(name);
            branch.setCreator(owner);
            branch.setTimeOfBranchCreation(LocalDateTime.now());

            branch.setValid(true);
            branch.setCommits(new ArrayList<>());

            Repository repository = repositoryRepository.findById(repoId).get();
            branch.setFolderId(filesUploader.createBranchFolder(name, repository.getFolderId()));
            repository.getBranches().add(branch);

            if (repository.getDefaultBranch() == null) {
                repository.setDefaultBranch(branch);
            }

            branchRepository.save(branch);
            repositoryRepository.save(repository);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete branch.
     *
     * @param branchId the branch id
     */
    public void deleteBranch(long branchId) {
        Branch r = branchRepository.findById(branchId).get(); //TODO:
        r.setValid(false);
        branchRepository.save(r);
    }

}

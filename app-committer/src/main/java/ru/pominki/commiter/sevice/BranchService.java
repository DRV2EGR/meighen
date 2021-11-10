package ru.pominki.commiter.sevice;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.entity.Branch;
import ru.pominki.commiter.entity.Repository;
import ru.pominki.commiter.repository.BranchRepository;
import ru.pominki.commiter.repository.RepositoryRepository;
import ru.pominki.commiter.sevice.Storage.FilesUploader;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    RepositoryRepository repositoryRepository;

    @Autowired
    FilesUploader filesUploader;

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

            branchRepository.save(branch);
            repositoryRepository.save(repository);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteBranch(long branchId) {
        Branch r = branchRepository.findById(branchId).get(); //TODO:
        r.setValid(false);
        branchRepository.save(r);
    }

}

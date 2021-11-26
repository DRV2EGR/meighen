package ru.meighgen.commiter.sevice;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.meighgen.commiter.entity.Repository;
import ru.meighgen.commiter.entity.User;
import ru.meighgen.commiter.repository.RepositoryRepository;
import ru.meighgen.commiter.repository.UserRepository;

/**
 * The type Repository service.
 */
@Service
public class RepositoryService {
    /**
     * The Repository repository.
     */
    @Autowired
    RepositoryRepository repositoryRepository;

    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * The Branch service.
     */
    @Autowired
    BranchService branchService;

    /**
     * Create repo boolean.
     *
     * @param name     the name
     * @param owner    the owner
     * @param folderId the folder id
     * @return the boolean
     */
    public boolean createRepo(String name, Long owner, String folderId) {
        try {
            Repository repository = new Repository();
            User u = userRepository.getById(owner);
//            System.out.println(u);

            repository.setName(name);
            repository.setOwner(owner);
            repository.setFolderId(folderId);
            repository.setValid(true);
            repository.setTimeOfRepoCreation(LocalDateTime.now());
            repository.setCollaborators(new ArrayList<>());
            repository.setBranches(new ArrayList<>());
            repositoryRepository.save(repository);
            u.getRepositories().add(repository);
            userRepository.save(u);


            branchService.createBranch("main", owner, repository.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete repo.
     *
     * @param repoId the repo id
     */
    public void deleteRepo(long repoId) {
        Repository r = repositoryRepository.findById(repoId).get(); //TODO:
        r.setValid(false);
        repositoryRepository.save(r);
    }
}

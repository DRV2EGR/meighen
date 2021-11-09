package ru.pominki.commiter.sevice;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.entity.Repository;
import ru.pominki.commiter.entity.User;
import ru.pominki.commiter.repository.RepositoryRepository;
import ru.pominki.commiter.repository.UserRepository;

@Service
public class RepositoryService {
    @Autowired
    RepositoryRepository repositoryRepository;

    @Autowired
    UserRepository userRepository;

    public boolean createRepo(String name, Long owner, String folderId) {
        try {
            Repository repository = new Repository();
            User u = userRepository.getById(owner);
//            System.out.println(u);

            repository.setName(name);
            repository.setOwner(owner);
            repository.setFolderId(folderId);
            repository.setTimeOfRepoCreation(LocalDateTime.now());
            repository.setCollaborators(new ArrayList<>());
            repository.setCommits(new ArrayList<>());

            repositoryRepository.save(repository);
            u.getRepositories().add(repository);
            userRepository.save(u);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

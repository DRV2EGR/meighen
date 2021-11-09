package ru.pominki.presenter.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pominki.presenter.dto.RepoDto;
import ru.pominki.presenter.entity.Repository;
import ru.pominki.presenter.entity.User;

@Service
public class ClassCastToDto {
    @Autowired
    UserService userService;

    public RepoDto convertRepoToRepoDto(Repository repository) {
        RepoDto repoDto = new RepoDto(
                repository.getId(),
                repository.getName(),
                repository.getTimeOfRepoCreation(),
                repository.getHEAD(),
                repository.getCommits(),
                new ArrayList<>()
        );

        for (User u : repository.getCollaborators()) {
            repoDto.getCollaborators().add(userService.convertUserToUserDto(u));
        }

        return repoDto;
    }
}

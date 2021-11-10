package ru.pominki.presenter.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pominki.presenter.entity.Commit;
import ru.pominki.presenter.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoDto {
    protected Long ID;
    protected String name;
    protected LocalDateTime timeOfRepoCreation;

    @OneToOne
    protected Commit HEAD;

    @OneToMany
    protected List<BranchDto> branches;

    @OneToMany
    protected List<UserDto> collaborators;
}

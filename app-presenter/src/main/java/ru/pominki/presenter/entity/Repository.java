package ru.pominki.presenter.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pominki.presenter.model.CommitModel;

@Entity
@Table(name = "repositories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repository extends BaseEntity {
    protected Long owner;

    protected String name;
    protected LocalDateTime timeOfRepoCreation;

    protected String folderId;

    @OneToOne
    protected Commit HEAD;

    @OneToMany
    protected List<Commit> commits;

    @OneToMany
    protected List<User> collaborators;
}

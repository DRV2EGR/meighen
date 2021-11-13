package ru.pominki.commiter.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    protected List<Branch> branches;

    @OneToOne
    protected Branch defaultBranch;

    @OneToMany
    protected List<User> collaborators;

    protected boolean valid;
}

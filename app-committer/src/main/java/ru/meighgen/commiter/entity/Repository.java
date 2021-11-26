package ru.meighgen.commiter.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Repository.
 */
@Entity
@Table(name = "repositories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repository extends BaseEntity {
    /**
     * The Owner.
     */
    protected Long owner;

    /**
     * The Name.
     */
    protected String name;
    /**
     * The Time of repo creation.
     */
    protected LocalDateTime timeOfRepoCreation;

    /**
     * The Folder id.
     */
    protected String folderId;

    /**
     * The Head.
     */
    @OneToOne
    protected Commit HEAD;

    /**
     * The Branches.
     */
    @OneToMany
    protected List<Branch> branches;

    /**
     * The Default branch.
     */
    @OneToOne
    protected Branch defaultBranch;

    /**
     * The Collaborators.
     */
    @OneToMany
    protected List<User> collaborators;

    /**
     * The Valid.
     */
    protected boolean valid;
}

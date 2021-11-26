package ru.meighgen.commiter.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Branch.
 */
@Entity
@Table(name = "branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends BaseEntity{
    /**
     * The Creator.
     */
    protected Long creator;

    /**
     * The Name.
     */
    protected String name;
    /**
     * The Time of branch creation.
     */
    protected LocalDateTime timeOfBranchCreation;

    /**
     * The Folder id.
     */
    protected String folderId;

    /**
     * The Commits.
     */
    @OneToMany
    protected List<Commit> commits;
    /**
     * The Head.
     */
    protected String HEAD;

    /**
     * The Valid.
     */
    protected boolean valid;
}

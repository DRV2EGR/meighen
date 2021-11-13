package ru.pominki.presenter.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends BaseEntity{
    protected Long creator;

    protected String name;
    protected LocalDateTime timeOfBranchCreation;

    protected String folderId;

    @OneToMany
    protected List<Commit> commits;
    protected String HEAD;

    protected boolean valid;
}

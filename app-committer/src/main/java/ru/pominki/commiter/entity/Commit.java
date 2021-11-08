package ru.pominki.commiter.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "commits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commit extends BaseEntity {
    protected String commitId;
    protected String folderId;

    @OneToOne
    protected Commit previouse;

    @OneToOne
    protected Commit next;
}

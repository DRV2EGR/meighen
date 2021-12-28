package ru.meighgen.commiter.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Commit.
 */
@Entity
@Table(name = "commits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commit extends BaseEntity {
    /**
     * The Commit id.
     */
    protected String commitId;
    /**
     * The Folder id.
     */
    protected String folderId;
    /**
     * The Message.
     */
    protected String message;

    /**
     * The Next.
     */
    @OneToOne
    protected Commit next;
}

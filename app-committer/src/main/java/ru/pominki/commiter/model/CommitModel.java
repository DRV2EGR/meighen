package ru.pominki.commiter.model;

import lombok.Data;

@Data
public class CommitModel {
    protected Long branchId;
    protected String commitId;
    protected String folderId;
    protected String message;
}

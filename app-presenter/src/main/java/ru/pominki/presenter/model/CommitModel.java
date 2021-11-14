package ru.pominki.presenter.model;

import java.io.File;
import java.util.List;

import lombok.Data;

@Data
public class CommitModel {
    protected Long branchId;
    protected String commitId;
    protected String folderId;
    protected String message;

    protected boolean hasNeedToCopy;
    protected String oldCommitFolder;
    protected List<File> filesToUpload;
}

package ru.meighgen.presenter.model;

import java.io.File;
import java.util.List;

import lombok.Data;

/**
 * The type Commit model.
 */
@Data
public class CommitModel {
    /**
     * The Branch id.
     */
    protected Long branchId;
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
     * The Has need to copy.
     */
    protected boolean hasNeedToCopy;
    /**
     * The Old commit folder.
     */
    protected String oldCommitFolder;
    /**
     * The Files to upload.
     */
    protected List<File> filesToUpload;
}

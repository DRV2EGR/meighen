package ru.meighgen.commiter.sevice.Storage;

import java.io.IOException;
import java.util.Map;

import ru.meighgen.commiter.model.CommitModel;

/**
 * The interface Files uploader.
 */
public interface FilesUploader {

    /**
     * Create repository folder string.
     *
     * @param name the name
     * @return the string
     */
    public String createRepositoryFolder(String name);

    /**
     * Upload boolean.
     *
     * @param file           the file
     * @param commitFilderId the commit filder id
     * @param mt             the mt
     * @return the boolean
     */
    public boolean upload(java.io.File file, String commitFilderId, String mt);

    /**
     * Create commit folder commit model.
     *
     * @return the commit model
     * @throws IOException the io exception
     */
    public CommitModel createCommitFolder() throws IOException;

    /**
     * Create branch folder string.
     *
     * @param name         the name
     * @param repoFolderId the repo folder id
     * @return the string
     */
    public String createBranchFolder(String name, String repoFolderId);

    /**
     * Download file string.
     *
     * @param fileId   the file id
     * @param fileName the file name
     * @return the string
     */
    public String downloadFile(String fileId, String fileName);

    /**
     * Show files from commit map.
     *
     * @param commitFolder the commit folder
     * @return the map
     */
    public Map<String, String> showFilesFromCommit(String commitFolder);

    /**
     * Copy commit.
     *
     * @param commitFolderId    the commit folder id
     * @param oldCommitFolderId the old commit folder id
     * @throws IOException the io exception
     */
    public void copyCommit(String commitFolderId, String oldCommitFolderId) throws IOException;

}

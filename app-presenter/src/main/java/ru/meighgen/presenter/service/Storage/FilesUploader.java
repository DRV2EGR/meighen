package ru.meighgen.presenter.service.Storage;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import ru.meighgen.presenter.model.CommitModel;

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
     * @param file        the file
     * @param commitModel the commit model
     * @return the boolean
     */
    public boolean upload(MultipartFile file, CommitModel commitModel);

    /**
     * Create commit folder commit model.
     *
     * @param branchFolder the branch folder
     * @return the commit model
     */
    public CommitModel createCommitFolder(String branchFolder);

    /**
     * Show files from commit map.
     *
     * @param commitFolder the commit folder
     * @return the map
     */
    public Map<String, String> showFilesFromCommit(String commitFolder);

    /**
     * Gets file.
     *
     * @param fileId   the file id
     * @param fileName the file name
     * @return the file
     */
//    public List<File> getFilesFromCommit(String commitFolder);
    public String downloadFile(String fileId, String fileName);

    /**
     * Gets all zipped files from commit.
     *
     * @param commitFolder the commit folder
     * @return the all zipped files from commit
     * @throws IOException the io exception
     */
    public String getAllZippedFilesFromCommit(String commitFolder) throws IOException;

    /**
     * Copy commit.
     *
     * @param commitFolderId    the commit folder id
     * @param oldCommitFolderId the old commit folder id
     * @throws IOException the io exception
     */
    public void copyCommit(String commitFolderId, String oldCommitFolderId) throws IOException;
}

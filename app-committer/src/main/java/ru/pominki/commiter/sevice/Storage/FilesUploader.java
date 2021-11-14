package ru.pominki.commiter.sevice.Storage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import ru.pominki.commiter.model.CommitModel;

public interface FilesUploader {

    public String createRepositoryFolder(String name);
    public boolean upload(java.io.File file, String commitFilderId, String mt);
    public CommitModel createCommitFolder() throws IOException;
    public String createBranchFolder(String name, String repoFolderId);

    public String downloadFile(String fileId, String fileName);
    public Map<String, String> showFilesFromCommit(String commitFolder);
    public void copyCommit(String commitFolderId, String oldCommitFolderId) throws IOException;

}

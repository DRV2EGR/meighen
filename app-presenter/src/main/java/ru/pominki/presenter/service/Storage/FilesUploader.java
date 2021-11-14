package ru.pominki.presenter.service.Storage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import ru.pominki.presenter.model.CommitModel;

public interface FilesUploader {

    public String createRepositoryFolder(String name);
    public boolean upload(MultipartFile file, CommitModel commitModel);
    public CommitModel createCommitFolder(String branchFolder);
    public Map<String, String> showFilesFromCommit(String commitFolder);
//    public List<File> getFilesFromCommit(String commitFolder);
    public String downloadFile(String fileId, String fileName);
    public String getAllZippedFilesFromCommit(String commitFolder) throws IOException;
    public void copyCommit(String commitFolderId, String oldCommitFolderId) throws IOException;
}

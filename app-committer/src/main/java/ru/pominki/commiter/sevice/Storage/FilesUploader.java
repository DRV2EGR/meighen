package ru.pominki.commiter.sevice.Storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import ru.pominki.commiter.model.CommitModel;

public interface FilesUploader {

    public String createRepositoryFolder(String name);
    public boolean upload(MultipartFile file, CommitModel commitModel);
    public CommitModel createCommitFolder() throws IOException;
    public String createBranchFolder(String name, String repoFolderId);
}

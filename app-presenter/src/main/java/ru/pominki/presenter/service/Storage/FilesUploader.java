package ru.pominki.presenter.service.Storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FilesUploader {

    public boolean upload(MultipartFile file);
    public boolean createFolder() throws IOException;
}

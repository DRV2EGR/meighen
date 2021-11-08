package ru.pominki.presenter.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pominki.presenter.service.Storage.FilesUploader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pominki.presenter.service.Storage.GoogleDriveServiceImp;
import ru.pominki.presenter.service.Storage.PCloudDriveService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/files")
public class UploadController {
    @Autowired
    FilesUploader driveService;

    @PostMapping(value = "/upload_to_drive", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam List<MultipartFile> files) throws IOException {

//        driveService = new PCloudDriveService();
        for (MultipartFile file : files) {
            //file.getName(), file, file.getContentType()
//            Boolean file2 = driveService.upload(file);
            driveService.createFolder();
        }

        return ResponseEntity.ok().build();
    }



}

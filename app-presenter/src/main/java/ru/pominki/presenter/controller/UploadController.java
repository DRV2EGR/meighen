package ru.pominki.presenter.controller;


import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pominki.presenter.model.CommitModel;
import ru.pominki.presenter.model.KafkaMsg;
import ru.pominki.presenter.service.ProducerService;
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

    @Autowired
    private ProducerService producerService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam Long repo, @RequestParam String message,
                                     @RequestParam List<MultipartFile> files ) throws IOException {
        CommitModel commit = driveService.createFolder();
        commit.setMesssage(message);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(commit);
        System.out.println(json);
        producerService.produce(new KafkaMsg(json));

//        driveService = new PCloudDriveService();
        for (MultipartFile file : files) {
            //file.getName(), file, file.getContentType()
            Boolean file2 = driveService.upload(file, commit);
        }

        return ResponseEntity.ok(commit);
    }

}

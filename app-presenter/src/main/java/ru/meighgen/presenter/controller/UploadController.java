package ru.meighgen.presenter.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.meighgen.presenter.service.Storage.FilesUploader;
import ru.meighgen.presenter.service.producer.ProducerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Upload controller.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/files")
public class UploadController {
    /**
     * The Drive service.
     */
    @Autowired
    FilesUploader driveService;

    @Autowired
    private ProducerService producerService;

    /**
     * Upload file response entity.
     *
     * @param repo    the repo
     * @param message the message
     * @param files   the files
     * @return the response entity
     * @throws IOException the io exception
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam Long repo, @RequestParam String message,
                                     @RequestParam List<MultipartFile> files ) throws IOException {
//        CommitModel commit = driveService.createCommitFolder("1Rn_ejgKRlsbVkPQZm1F_dLhT0uIFzSHB");
//        commit.setMessage(message);
//
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(commit);
//        System.out.println(json);
//        producerService.produce(new KafkaMsg(json));
//
////        driveService = new PCloudDriveService();
//        for (MultipartFile file : files) {
//            //file.getName(), file, file.getContentType()
//            Boolean file2 = driveService.upload(file, commit);
//        }
//
//        return ResponseEntity.ok(commit);

        return ResponseEntity.internalServerError().build();
    }

}

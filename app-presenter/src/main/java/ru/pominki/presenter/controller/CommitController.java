package ru.pominki.presenter.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pominki.presenter.entity.Branch;
import ru.pominki.presenter.model.CommitModel;
import ru.pominki.presenter.model.KafkaMsg;
import ru.pominki.presenter.repository.BranchRepository;
import ru.pominki.presenter.service.Storage.FilesUploader;
import ru.pominki.presenter.service.producer.CommitProducerService;
import ru.pominki.presenter.service.producer.ProducerService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/private/commit")
public class CommitController {
    @Autowired
    FilesUploader driveService;

    @Autowired
    CommitProducerService commitProducerService;

    @Autowired
    BranchRepository branchRepository;

    @PostMapping("/create")
    public ResponseEntity<?> doCommit(
            @RequestParam Long branchId, @RequestParam String message,
            @RequestParam List<MultipartFile> files ) throws IOException, ParseException {
//        CommitModel commit = driveService.createCommitFolder();
        Branch b = branchRepository.findBranchById(branchId);

        CommitModel commit = driveService.createCommitFolder(b.getFolderId());
        commit.setMessage(message);
        commit.setBranchId(branchId);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(commit);
        System.out.println(json);
        commitProducerService.createCommit(new KafkaMsg(json));

//        driveService = new PCloudDriveService();
        for (MultipartFile file : files) {
            //file.getName(), file, file.getContentType()
            Boolean file2 = driveService.upload(file, commit);
        }


        return ResponseEntity.ok(commit);
    }
}

package ru.pominki.presenter.controller;

import java.io.*;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.tika.Tika;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pominki.presenter.dto.CommitFilesDto;
import ru.pominki.presenter.entity.Branch;
import ru.pominki.presenter.entity.Commit;
import ru.pominki.presenter.model.CommitModel;
import ru.pominki.presenter.model.KafkaMsg;
import ru.pominki.presenter.repository.BranchRepository;
import ru.pominki.presenter.repository.CommitRepository;
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

    @Autowired
    CommitRepository commitRepository;

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

    @GetMapping("/files")
    public ResponseEntity<?> showFiles(@RequestParam String commitId) {
        Commit c = commitRepository.findCommitByCommitId(commitId);
        Map<String, String> mkv = driveService.showFilesFromCommit(c.getFolderId());
        List<CommitFilesDto> commitFilesDtos = new ArrayList<>();
        for (var entry : mkv.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
            commitFilesDtos.add(new CommitFilesDto(entry.getKey(), entry.getValue()));
        }

        return ResponseEntity.ok(commitFilesDtos);
    }

//    @GetMapping("/download_all_files")
//    public ResponseEntity<?> showFiles1(@RequestParam String commitId) {
//        Commit c = commitRepository.findCommitByCommitId(commitId);
//        return ResponseEntity.ok(driveService.showFilesFromCommit(c.getFolderId()));
//    }

    @GetMapping("/download_old")
    public ResponseEntity<?> downloadAllByCommitId(@RequestParam String commitId) throws IOException {
        return ResponseEntity.ok(driveService.getAllZippedFilesFromCommit(
                commitRepository.findCommitByCommitId(commitId).getFolderId()
        ));
    }

    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadOneFile(
            @RequestParam String fileId, @RequestParam String fileName) throws IOException {
        Tika tika = new Tika();

        String fullPath = driveService.downloadFile(fileId, fileName).substring(1);
        File file = new File(fullPath);
        long fileLength = file.length(); // this is ok, but see note below

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(tika.detect(file)));
        respHeaders.setContentLength(fileLength);
        respHeaders.setContentDispositionFormData("attachment", file.getName());

        return new ResponseEntity<FileSystemResource>(
                new FileSystemResource(file), respHeaders, HttpStatus.OK
        );
    }

    @GetMapping("/download_zip")
    public ResponseEntity<FileSystemResource> downloadZipWithFiles(
            @RequestParam String commitId) throws IOException {
        Tika tika = new Tika();

        Commit c = commitRepository.findCommitByCommitId(commitId);
        String fullPath = driveService.getAllZippedFilesFromCommit(c.getFolderId());
        File file = new File(fullPath);
        long fileLength = file.length();

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(tika.detect(file)));
        respHeaders.setContentLength(fileLength);
        respHeaders.setContentDispositionFormData("attachment", c.getCommitId()+".zip");

        return new ResponseEntity<FileSystemResource>(
                new FileSystemResource(file), respHeaders, HttpStatus.OK
        );
    }

}

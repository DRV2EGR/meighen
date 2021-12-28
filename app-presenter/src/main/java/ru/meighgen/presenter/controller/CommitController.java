package ru.meighgen.presenter.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.tika.Tika;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.meighgen.presenter.entity.Branch;
import ru.meighgen.presenter.dto.CommitFilesDto;
import ru.meighgen.presenter.entity.Commit;
import ru.meighgen.presenter.model.CommitModel;
import ru.meighgen.presenter.model.KafkaMsg;
import ru.meighgen.presenter.repository.BranchRepository;
import ru.meighgen.presenter.repository.CommitRepository;
import ru.meighgen.presenter.service.Storage.FilesUploader;
import ru.meighgen.presenter.service.producer.CommitProducerService;

/**
 * The type Commit controller.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/private/commit")
public class CommitController {
    /**
     * The Drive service.
     */
    @Autowired
    FilesUploader driveService;

    /**
     * The Commit producer service.
     */
    @Autowired
    CommitProducerService commitProducerService;

    /**
     * The Branch repository.
     */
    @Autowired
    BranchRepository branchRepository;

    /**
     * The Commit repository.
     */
    @Autowired
    CommitRepository commitRepository;

    /**
     * Do commit response entity.
     *
     * @param branchId the branch id
     * @param message  the message
     * @param files    the files
     * @return the response entity
     * @throws IOException    the io exception
     * @throws ParseException the parse exception
     */
    @PostMapping("/create")
    public ResponseEntity<?> doCommit(
            @RequestParam Long branchId, @RequestParam String message,
            @RequestParam List<MultipartFile> files ) throws IOException, ParseException {
//        CommitModel commit = driveService.createCommitFolder();
        Branch b = branchRepository.findBranchById(branchId);

        CommitModel commit;
        commit = driveService.createCommitFolder(b.getFolderId());
        commit.setMessage(message);
        commit.setBranchId(branchId);
        commit.setFilesToUpload(new ArrayList<>());

        for (MultipartFile file : files) {
            java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
            file.transferTo(convFile);

            commit.getFilesToUpload().add(convFile);
        }

        if (b.getHEAD() != null) {
            commit.setHasNeedToCopy(true);
            commit.setOldCommitFolder(
                    commitRepository.findCommitByCommitId(b.getHEAD()).getFolderId()
            );
        }

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(commit);
        System.out.println(json);
        commitProducerService.createCommit(new KafkaMsg(json));

//        driveService = new PCloudDriveService();
//        for (MultipartFile file : files) {
//            //file.getName(), file, file.getContentType()
//            Boolean file2 = driveService.upload(file, commit);
//        }
//
//        if (b.getHEAD() != null) {
//            driveService.copyCommit(
//                    commit.getFolderId(),
//                    commitRepository.findCommitByCommitId(b.getHEAD()).getFolderId()
//
//            );
//        }
//

        return ResponseEntity.ok(commit);
    }

    /**
     * Show files response entity.
     *
     * @param commitId the commit id
     * @return the response entity
     */
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

    /**
     * Download all by commit id response entity.
     *
     * @param commitId the commit id
     * @return the response entity
     * @throws IOException the io exception
     */
    @GetMapping("/download_old")
    public ResponseEntity<?> downloadAllByCommitId(@RequestParam String commitId) throws IOException {
        return ResponseEntity.ok(driveService.getAllZippedFilesFromCommit(
                commitRepository.findCommitByCommitId(commitId).getFolderId()
        ));
    }

    /**
     * Download one file response entity.
     *
     * @param fileId   the file id
     * @param fileName the file name
     * @return the response entity
     * @throws IOException the io exception
     */
    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadOneFile(
            @RequestParam String fileId, @RequestParam String fileName) throws IOException {
        Tika tika = new Tika();

        String fullPath = driveService.downloadFile(fileId, fileName).substring(1);
        File file = new File(fullPath);
        long fileLength = file.length();
        file.delete();

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(tika.detect(file)));
        respHeaders.setContentLength(fileLength);
        respHeaders.setContentDispositionFormData("attachment", file.getName());

        return new ResponseEntity<FileSystemResource>(
                new FileSystemResource(file), respHeaders, HttpStatus.OK
        );
    }

    /**
     * Download zip with files response entity.
     *
     * @param commitId the commit id
     * @return the response entity
     * @throws IOException the io exception
     */
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

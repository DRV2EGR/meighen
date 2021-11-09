package ru.pominki.presenter.service.Storage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;
import ru.pominki.presenter.model.CommitModel;
//import com.spring.service.GoogleDriveService;


@Service
public class GoogleDriveServiceImp implements FilesUploader {

//    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveServiceImp.class);

    @Value("${google.service_account_email}")
    private String serviceAccountEmail;

    @Value("${google.application_name}")
    private String applicationName;

    @Value("${google.service_account_key}")
    private String serviceAccountKey;

    @Value("${google.folder_id}")
    private String folderID;

    public Drive getDriveService() {
        Drive service = null;
        try {
            System.out.println(this.serviceAccountKey);
            URL resource = GoogleDriveServiceImp.class.getResource("/static/" + this.serviceAccountKey);
            java.io.File key = Paths.get(resource.toURI()).toFile();
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
                    .setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
                    .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                    .setServiceAccountPrivateKeyFromP12File(key).build();
            service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(applicationName)
                    .setHttpRequestInitializer(credential).build();
        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
            e.printStackTrace();

        }

        return service;

    }

    public  java.io.File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    public File upLoadFile(String fileName, MultipartFile filePath, String mimeType, CommitModel commitModel) {
        File file = new File();
        try {
            String fn = filePath.getOriginalFilename();
            java.io.File fileUpload = multipartToFile(filePath, fn);
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setMimeType(mimeType);
            fileMetadata.setName(fn);
            fileMetadata.setParents(Collections.singletonList(commitModel.getFolderId()));
            com.google.api.client.http.FileContent fileContent = new FileContent(mimeType, fileUpload);
            file = getDriveService().files().create(fileMetadata, fileContent)
                    .setFields("id,webContentLink,webViewLink").execute();
        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return file;
    }

    public CommitModel createCommitFolder() {
        CommitModel cm = new CommitModel();
        try {
            String uuid = UUID.randomUUID().toString();
            cm.setCommitId(uuid);

            File fileMetadata = new File();
            fileMetadata.setName("coomit-"+uuid);
            fileMetadata.setMimeType("application/vnd.google-apps.folder");
            fileMetadata.setParents(Collections.singletonList("1Rn_ejgKRlsbVkPQZm1F_dLhT0uIFzSHB"));

            File file = getDriveService().files().create(fileMetadata)
                    .setFields("id, name")
                    .execute();

            System.out.println("Folder ID: " + file.getId());
            cm.setFolderId(file.getId());

            return cm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean upload(MultipartFile file, CommitModel commitModel) {
        try {
            upLoadFile(file.getName(), file, file.getContentType(), commitModel);
        } catch (Exception e) {
            // pass;
            return false;
        }
        return true;
    }

    public String createRepositoryFolder(String name){
        try {
            String uuid = UUID.randomUUID().toString();
            String rn = "repository-"+name+"-"+uuid;

            File fileMetadata = new File();
            fileMetadata.setName(rn);
            fileMetadata.setMimeType("application/vnd.google-apps.folder");
            fileMetadata.setParents(Collections.singletonList("1Rn_ejgKRlsbVkPQZm1F_dLhT0uIFzSHB"));

            File file = getDriveService().files().create(fileMetadata)
                    .setFields("id, name")
                    .execute();

//            System.out.println("Folder ID: " + file.getId());

            return file.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
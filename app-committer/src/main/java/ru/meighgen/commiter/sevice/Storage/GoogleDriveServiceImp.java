package ru.meighgen.commiter.sevice.Storage;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.meighgen.commiter.model.CommitModel;
//import com.spring.service.GoogleDriveService;


/**
 * The type Google drive service imp.
 */
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

    /**
     * Gets drive service.
     *
     * @return the drive service
     */
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

    /**
     * Multipart to file java . io . file.
     *
     * @param multipart the multipart
     * @param fileName  the file name
     * @return the java . io . file
     * @throws IllegalStateException the illegal state exception
     * @throws IOException           the io exception
     */
    public  java.io.File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    /**
     * Up load file file.
     *
     * @param fileName       the file name
     * @param fileUpload     the file upload
     * @param mimeType       the mime type
     * @param commitFolderId the commit folder id
     * @return the file
     */
    public File upLoadFile(String fileName, java.io.File fileUpload, String mimeType, String commitFolderId) {
        File file = new File();
        try {
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setMimeType(mimeType);
            fileMetadata.setName(fileName);
            fileMetadata.setParents(Collections.singletonList(commitFolderId));
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
    public boolean upload(java.io.File file, String commitFilderId, String mt) {
        try {
            upLoadFile(
                    file.getName(),
                    file,
                    mt,
                    commitFilderId
            );
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

    public String createBranchFolder(String name, String repoFolderId) {
        try {
            String uuid = UUID.randomUUID().toString();
            String rn = "branch-"+name+"-"+uuid;

            File fileMetadata = new File();
            fileMetadata.setName(rn);
            fileMetadata.setMimeType("application/vnd.google-apps.folder");
            fileMetadata.setParents(Collections.singletonList(repoFolderId));

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

    public String downloadFile(String fileId, String fileName) {
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            getDriveService().files().get(fileId)
                    .executeMediaAndDownloadTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource(".").getFile()+fileName;

        try(OutputStream outputStream1 = new FileOutputStream(path);
        ) {
            ((ByteArrayOutputStream) outputStream).writeTo(outputStream1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public Map<String, String> showFilesFromCommit(String commitFolder) {
        Drive driveService = getDriveService();

        String pageToken = null;
        List<String> list = new ArrayList<>();

        String query = null;
        if (commitFolder == null) {
            query = " mimeType = 'application/vnd.google-apps.folder' " //
                    + " and 'root' in parents";
        } else {
            query = " mimeType != 'application/vnd.google-apps.folder' " //
                    + " and '" + commitFolder + "' in parents";
        }

        Map<String, String> li = new HashMap<>();

        do {
            FileList result = null;
            try {
                result = driveService.files().list().setQ(query).setSpaces("drive") //
                        // Fields will be assigned values: id, name, createdTime
                        .setFields("nextPageToken, files(id, name, createdTime)")//
                        .setPageToken(pageToken).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (File file : result.getFiles()) {
                list.add(file.getName());
                li.put(file.getName(), file.getId());
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return li;
    }

    public void copyCommit(String commitFolderId, String oldCommitFolderId) throws IOException {
        FileList result = null;
        Map<String, String> mapFiles = showFilesFromCommit(commitFolderId);
        try {
            String pageToken = null;
            do {
                try {
                    String query = " mimeType != 'application/vnd.google-apps.folder' "
                            + " and '" + oldCommitFolderId + "' in parents";
                    result = getDriveService().files().list().setQ(query).setSpaces("drive")
                            .setFields("nextPageToken, files(id, name, createdTime)")
                            .setPageToken(pageToken).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (File file : result.getFiles()) {
                    if (mapFiles.containsKey(file.getName())) {
                        continue;
                    }
                    String file1 = downloadFile(file.getId(), file.getName());
                    java.io.File fileUpload = new java.io.File(file1);
                    com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
                    fileMetadata.setMimeType(file.getMimeType());
                    fileMetadata.setName(file.getName());
                    fileMetadata.setParents(Collections.singletonList(commitFolderId));
                    com.google.api.client.http.FileContent fileContent = new FileContent(file.getMimeType(), fileUpload);
                    getDriveService().files().create(fileMetadata, fileContent)
                            .setFields("id,webContentLink,webViewLink").execute();
                    java.io.File ftd = new java.io.File(file1);
                    ftd.delete();
                }
                pageToken = result.getNextPageToken();
            } while (pageToken != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
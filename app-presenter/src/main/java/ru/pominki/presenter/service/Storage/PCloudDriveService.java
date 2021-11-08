package ru.pominki.presenter.service.Storage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.pcloud.sdk.*;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PCloudDriveService {
    @Value("${pcloud.token}")
    private String token;

    private ApiClient getApiClient() {
        ApiClient ac =  PCloudSdk.newClientBuilder()
                .apiHost("api.pcloud.com")
                .authenticator(Authenticators.newOAuthAuthenticator(token))
                .create();
        System.out.println(ac.apiHost());
        return ac;
    }

    public  java.io.File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }


    public boolean upload(MultipartFile file) {
        try{
            File fl = multipartToFile(file, file.getOriginalFilename());

            getApiClient().createFile(RemoteFolder.ROOT_FOLDER_ID, fl.getName(), DataSource.create(fl), new Date(fl.lastModified()), new ProgressListener() {
                public void onProgress(long done, long total) {
                    System.out.format("\rUploading... %.1f\n", ((double) done / (double) total) * 100d);
                }
            }).execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

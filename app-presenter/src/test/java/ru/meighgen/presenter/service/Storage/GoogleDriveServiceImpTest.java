//package ru.pominki.presenter.service.Storage;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.HttpRequestFactory;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.JsonObjectParser;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.drive.Drive;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {GoogleDriveServiceImp.class})
//@ExtendWith(SpringExtension.class)
//class GoogleDriveServiceImpTest {
//    @Autowired
//    private GoogleDriveServiceImp googleDriveServiceImp;
//
//    @Test
//    void testGetDriveService() {
//        Drive actualDriveService = this.googleDriveServiceImp.getDriveService();
//        assertEquals("Pominki", actualDriveService.getApplicationName());
//        assertFalse(actualDriveService.getSuppressRequiredParameterChecks());
//        assertEquals("https://www.googleapis.com/", actualDriveService.getRootUrl());
//        assertFalse(actualDriveService.getSuppressPatternChecks());
//        assertNull(actualDriveService.getGoogleClientRequestInitializer());
//        assertEquals("drive/v3/", actualDriveService.getServicePath());
//        JsonFactory jsonFactory = actualDriveService.getJsonFactory();
//        assertTrue(jsonFactory instanceof JacksonFactory);
//        JsonObjectParser objectParser = actualDriveService.getObjectParser();
//        assertTrue(objectParser.getWrapperKeys().isEmpty());
//        HttpRequestFactory requestFactory = actualDriveService.getRequestFactory();
//        HttpRequestInitializer initializer = requestFactory.getInitializer();
//        assertTrue(initializer instanceof GoogleCredential);
//        assertSame(jsonFactory, objectParser.getJsonFactory());
//        HttpTransport transport = requestFactory.getTransport();
//        assertTrue(transport instanceof NetHttpTransport);
//        assertNull(((GoogleCredential) initializer).getServiceAccountUser());
//        assertEquals(1, ((GoogleCredential) initializer).getServiceAccountScopes().size());
//        assertNull(((GoogleCredential) initializer).getServiceAccountPrivateKeyId());
//        assertEquals("drive-user@pominki.iam.gserviceaccount.com", ((GoogleCredential) initializer).getServiceAccountId());
//        assertNull(((GoogleCredential) initializer).getRequestInitializer());
//        assertNull(((GoogleCredential) initializer).getRefreshToken());
//        assertTrue(((GoogleCredential) initializer).getRefreshListeners().isEmpty());
//        assertNull(((GoogleCredential) initializer).getClientAuthentication());
//        assertSame(jsonFactory, ((GoogleCredential) initializer).getJsonFactory());
//        assertSame(transport, ((GoogleCredential) initializer).getTransport());
//        assertEquals("https://accounts.google.com/o/oauth2/token",
//                ((GoogleCredential) initializer).getTokenServerEncodedUrl());
//    }
//
//    @Test
//    void testCreateCommitFolder() {
//        assertNull(this.googleDriveServiceImp.createCommitFolder("janedoe/featurebranch"));
//    }
//
//    @Test
//    void testCreateRepositoryFolder() {
//        assertNull(this.googleDriveServiceImp.createRepositoryFolder("Name"));
//    }
//}
//

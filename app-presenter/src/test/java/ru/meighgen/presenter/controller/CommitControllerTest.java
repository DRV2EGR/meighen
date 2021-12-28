package ru.meighgen.presenter.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.meighgen.presenter.entity.Commit;
import ru.meighgen.presenter.repository.BranchRepository;
import ru.meighgen.presenter.repository.CommitRepository;
import ru.meighgen.presenter.service.Storage.GoogleDriveServiceImp;
import ru.meighgen.presenter.service.producer.CommitProducerService;

@ContextConfiguration(classes = {CommitController.class})
@ExtendWith(SpringExtension.class)
class CommitControllerTest {
    @MockBean
    private BranchRepository branchRepository;

    @Autowired
    private CommitController commitController;

    @MockBean
    private CommitProducerService commitProducerService;

    @MockBean
    private CommitRepository commitRepository;

    @MockBean
    private GoogleDriveServiceImp googleDriveServiceImp;

    @Test
    void testDownloadAllByCommitId() throws Exception {
        when(this.googleDriveServiceImp.getAllZippedFilesFromCommit((String) any())).thenReturn("jane.doe@example.org");

        Commit commit = new Commit();
        commit.setMessage("Not all who wander are lost");
        commit.setFolderId("42");
        commit.setNext(new Commit());
        commit.setId(123L);
        commit.setCommitId("42");

        Commit commit1 = new Commit();
        commit1.setMessage("Not all who wander are lost");
        commit1.setFolderId("42");
        commit1.setNext(commit);
        commit1.setId(123L);
        commit1.setCommitId("42");

        Commit commit2 = new Commit();
        commit2.setMessage("Not all who wander are lost");
        commit2.setFolderId("42");
        commit2.setNext(commit1);
        commit2.setId(123L);
        commit2.setCommitId("42");

        Commit commit3 = new Commit();
        commit3.setMessage("Not all who wander are lost");
        commit3.setFolderId("42");
        commit3.setNext(commit2);
        commit3.setId(123L);
        commit3.setCommitId("42");

        Commit commit4 = new Commit();
        commit4.setMessage("Not all who wander are lost");
        commit4.setFolderId("42");
        commit4.setNext(commit3);
        commit4.setId(123L);
        commit4.setCommitId("42");
        when(this.commitRepository.findCommitByCommitId((String) any())).thenReturn(commit4);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/private/commit/download_old")
                .param("commitId", "foo");
        MockMvcBuilders.standaloneSetup(this.commitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("jane.doe@example.org"));
    }

    @Test
    void testShowFiles() throws Exception {
        when(this.googleDriveServiceImp.showFilesFromCommit((String) any())).thenReturn(new HashMap<String, String>(1));

        Commit commit = new Commit();
        commit.setMessage("Not all who wander are lost");
        commit.setFolderId("42");
        commit.setNext(new Commit());
        commit.setId(123L);
        commit.setCommitId("42");

        Commit commit1 = new Commit();
        commit1.setMessage("Not all who wander are lost");
        commit1.setFolderId("42");
        commit1.setNext(commit);
        commit1.setId(123L);
        commit1.setCommitId("42");

        Commit commit2 = new Commit();
        commit2.setMessage("Not all who wander are lost");
        commit2.setFolderId("42");
        commit2.setNext(commit1);
        commit2.setId(123L);
        commit2.setCommitId("42");

        Commit commit3 = new Commit();
        commit3.setMessage("Not all who wander are lost");
        commit3.setFolderId("42");
        commit3.setNext(commit2);
        commit3.setId(123L);
        commit3.setCommitId("42");

        Commit commit4 = new Commit();
        commit4.setMessage("Not all who wander are lost");
        commit4.setFolderId("42");
        commit4.setNext(commit3);
        commit4.setId(123L);
        commit4.setCommitId("42");
        when(this.commitRepository.findCommitByCommitId((String) any())).thenReturn(commit4);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/private/commit/files")
                .param("commitId", "foo");
        MockMvcBuilders.standaloneSetup(this.commitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testShowFiles2() throws Exception {
        when(this.googleDriveServiceImp.showFilesFromCommit((String) any())).thenReturn(new HashMap<String, String>(0));

        Commit commit = new Commit();
        commit.setMessage("Not all who wander are lost");
        commit.setFolderId("42");
        commit.setNext(new Commit());
        commit.setId(123L);
        commit.setCommitId("42");

        Commit commit1 = new Commit();
        commit1.setMessage("Not all who wander are lost");
        commit1.setFolderId("42");
        commit1.setNext(commit);
        commit1.setId(123L);
        commit1.setCommitId("42");

        Commit commit2 = new Commit();
        commit2.setMessage("Not all who wander are lost");
        commit2.setFolderId("42");
        commit2.setNext(commit1);
        commit2.setId(123L);
        commit2.setCommitId("42");

        Commit commit3 = new Commit();
        commit3.setMessage("Not all who wander are lost");
        commit3.setFolderId("42");
        commit3.setNext(commit2);
        commit3.setId(123L);
        commit3.setCommitId("42");

        Commit commit4 = new Commit();
        commit4.setMessage("Not all who wander are lost");
        commit4.setFolderId("42");
        commit4.setNext(commit3);
        commit4.setId(123L);
        commit4.setCommitId("42");
        when(this.commitRepository.findCommitByCommitId((String) any())).thenReturn(commit4);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/private/commit/files")
                .param("commitId", "foo");
        MockMvcBuilders.standaloneSetup(this.commitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testShowFiles3() throws Exception {
        HashMap<String, String> stringStringMap = new HashMap<String, String>(1);
        stringStringMap.put("?", "42");
        when(this.googleDriveServiceImp.showFilesFromCommit((String) any())).thenReturn(stringStringMap);

        Commit commit = new Commit();
        commit.setMessage("Not all who wander are lost");
        commit.setFolderId("42");
        commit.setNext(new Commit());
        commit.setId(123L);
        commit.setCommitId("42");

        Commit commit1 = new Commit();
        commit1.setMessage("Not all who wander are lost");
        commit1.setFolderId("42");
        commit1.setNext(commit);
        commit1.setId(123L);
        commit1.setCommitId("42");

        Commit commit2 = new Commit();
        commit2.setMessage("Not all who wander are lost");
        commit2.setFolderId("42");
        commit2.setNext(commit1);
        commit2.setId(123L);
        commit2.setCommitId("42");

        Commit commit3 = new Commit();
        commit3.setMessage("Not all who wander are lost");
        commit3.setFolderId("42");
        commit3.setNext(commit2);
        commit3.setId(123L);
        commit3.setCommitId("42");

        Commit commit4 = new Commit();
        commit4.setMessage("Not all who wander are lost");
        commit4.setFolderId("42");
        commit4.setNext(commit3);
        commit4.setId(123L);
        commit4.setCommitId("42");
        when(this.commitRepository.findCommitByCommitId((String) any())).thenReturn(commit4);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/private/commit/files")
                .param("commitId", "foo");
        MockMvcBuilders.standaloneSetup(this.commitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"name\":\"?\",\"fileId\":\"42\"}]"));
    }
}


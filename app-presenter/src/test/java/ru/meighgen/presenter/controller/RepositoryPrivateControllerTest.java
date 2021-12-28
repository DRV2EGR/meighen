package ru.meighgen.presenter.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.meighgen.presenter.payload.CreateRepoPayload;
import ru.meighgen.presenter.repository.RepositoryRepository;
import ru.meighgen.presenter.service.ClassCastToDto;
import ru.meighgen.presenter.service.Storage.GoogleDriveServiceImp;
import ru.meighgen.presenter.service.UserService;
import ru.meighgen.presenter.service.producer.BranchProducerService;
import ru.meighgen.presenter.service.producer.ProducerService;

@ContextConfiguration(classes = {RepositoryPrivateController.class})
@ExtendWith(SpringExtension.class)
class RepositoryPrivateControllerTest {
    @MockBean
    private BranchProducerService branchProducerService;

    @MockBean
    private ClassCastToDto classCastToDto;

    @MockBean
    private GoogleDriveServiceImp googleDriveServiceImp;

    @MockBean
    private ProducerService producerService;

    @Autowired
    private RepositoryPrivateController repositoryPrivateController;

    @MockBean
    private RepositoryRepository repositoryRepository;

    @MockBean
    private UserService userService;

    @Test
    void testCheckforName() throws Exception {
        when(this.repositoryRepository.countAllByName((String) any())).thenReturn(3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/private/repos/check_name")
                .param("name", "foo");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.repositoryPrivateController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    @Test
    void testCheckforName2() throws Exception {
        when(this.repositoryRepository.countAllByName((String) any())).thenReturn(0);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/private/repos/check_name")
                .param("name", "foo");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.repositoryPrivateController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    @Test
    void testCreate_repo() throws Exception {
        CreateRepoPayload createRepoPayload = new CreateRepoPayload();
        createRepoPayload.setOwner(1L);
        createRepoPayload.setFolderId("42");
        createRepoPayload.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(createRepoPayload);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/private/repos/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.repositoryPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testDeleteRepo() throws Exception {
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/private/repos/delete");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("repoId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.repositoryPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}


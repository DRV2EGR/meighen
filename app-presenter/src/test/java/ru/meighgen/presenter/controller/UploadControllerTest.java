package ru.meighgen.presenter.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.meighgen.presenter.service.Storage.GoogleDriveServiceImp;
import ru.meighgen.presenter.service.producer.ProducerService;

@ContextConfiguration(classes = {UploadController.class})
@ExtendWith(SpringExtension.class)
class UploadControllerTest {
    @MockBean
    private GoogleDriveServiceImp googleDriveServiceImp;

    @MockBean
    private ProducerService producerService;

    @Autowired
    private UploadController uploadController;

    @Test
    void testUploadFile() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.post("/api/files/upload")
                .param("message", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("repo", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.uploadController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }
}


package ru.meighgen.presenter.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

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
import ru.meighgen.presenter.model.KafkaMsg;
import ru.meighgen.presenter.service.producer.ProducerService;

@ContextConfiguration(classes = {KafkaKontroller.class})
@ExtendWith(SpringExtension.class)
class KafkaKontrollerTest {
    @Autowired
    private KafkaKontroller kafkaKontroller;

    @MockBean
    private ProducerService producerService;

    @Test
    void testGenerate() throws Exception {
        doNothing().when(this.producerService).produce((KafkaMsg) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/generate").param("message", "foo");
        MockMvcBuilders.standaloneSetup(this.kafkaKontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("OK"));
    }
}


package ru.meighgen.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.meighgen.presenter.model.KafkaMsg;
import ru.meighgen.presenter.service.producer.ProducerService;


/**
 * The type Kafka kontroller.
 */
@RestController
public class KafkaKontroller {
    @Autowired
    private ProducerService producerService;

    /**
     * Generate string.
     *
     * @param message the message
     * @return the string
     */
    @GetMapping("/generate")
    public String generate(@RequestParam String message) {
        producerService.produce(new KafkaMsg(message));
        return "OK";
    }
}

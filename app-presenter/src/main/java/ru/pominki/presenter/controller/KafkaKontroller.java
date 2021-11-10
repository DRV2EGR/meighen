package ru.pominki.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pominki.presenter.model.KafkaMsg;
import ru.pominki.presenter.service.producer.ProducerService;


@RestController
public class KafkaKontroller {
    @Autowired
    private ProducerService producerService;

    @GetMapping("/generate")
    public String generate(@RequestParam String message) {
        producerService.produce(new KafkaMsg(message));
        return "OK";
    }
}

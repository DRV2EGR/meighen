package ru.pominki.presenter.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.pominki.presenter.model.KafkaMsg;

@Service
public class CommitProducerService {
    @Autowired
    private KafkaTemplate<String, KafkaMsg> kafkaTemplate;

    public void createCommit(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("create_commits", message);
    }
}

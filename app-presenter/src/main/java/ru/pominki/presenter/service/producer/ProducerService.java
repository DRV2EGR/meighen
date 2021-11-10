package ru.pominki.presenter.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.pominki.presenter.model.KafkaMsg;

@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, KafkaMsg> kafkaTemplate;

    public void produce(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("messages", message);
    }

    public void produce_create_repo(KafkaMsg message) {
        kafkaTemplate.send("create_repos", message);
    }

    public void produceDeleteRepo(KafkaMsg message) {
        kafkaTemplate.send("delete_repos", message);
    }
}

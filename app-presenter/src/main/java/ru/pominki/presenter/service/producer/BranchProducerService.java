package ru.pominki.presenter.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.pominki.presenter.model.KafkaMsg;

@Service
public class BranchProducerService {
    @Autowired
    private KafkaTemplate<String, KafkaMsg> kafkaTemplate;

    public void createBranch(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("create_branches", message);
    }

    public void deleteBranch(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("delete_branches", message);
    }
}

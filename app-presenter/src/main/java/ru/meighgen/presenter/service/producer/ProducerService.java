package ru.meighgen.presenter.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.meighgen.presenter.model.KafkaMsg;

/**
 * The type Producer service.
 */
@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, KafkaMsg> kafkaTemplate;

    /**
     * Produce.
     *
     * @param message the message
     */
    public void produce(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("messages", message);
    }

    /**
     * Produce create repo.
     *
     * @param message the message
     */
    public void produce_create_repo(KafkaMsg message) {
        kafkaTemplate.send("create_repos", message);
    }

    /**
     * Produce delete repo.
     *
     * @param message the message
     */
    public void produceDeleteRepo(KafkaMsg message) {
        kafkaTemplate.send("delete_repos", message);
    }
}

package ru.meighgen.presenter.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.meighgen.presenter.model.KafkaMsg;

/**
 * The type Commit producer service.
 */
@Service
public class CommitProducerService {
    @Autowired
    private KafkaTemplate<String, KafkaMsg> kafkaTemplate;

    /**
     * Create commit.
     *
     * @param message the message
     */
    public void createCommit(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("create_commits", message);
    }
}

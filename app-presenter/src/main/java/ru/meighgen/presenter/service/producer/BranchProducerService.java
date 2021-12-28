package ru.meighgen.presenter.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.meighgen.presenter.model.KafkaMsg;

/**
 * The type Branch producer service.
 */
@Service
public class BranchProducerService {
    @Autowired
    private KafkaTemplate<String, KafkaMsg> kafkaTemplate;

    /**
     * Create branch.
     *
     * @param message the message
     */
    public void createBranch(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("create_branches", message);
    }

    /**
     * Delete branch.
     *
     * @param message the message
     */
    public void deleteBranch(KafkaMsg message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("delete_branches", message);
    }
}

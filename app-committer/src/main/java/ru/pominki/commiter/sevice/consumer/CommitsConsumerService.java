package ru.pominki.commiter.sevice.consumer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.model.KafkaMsg;
import ru.pominki.commiter.sevice.CommitService;

@Service
public class CommitsConsumerService {
    @Autowired
    CommitService commitService;

    @KafkaListener(topics = "create_commits", groupId = "message_group_id")
    public void printFile(KafkaMsg message) {
        try {
            System.out.println(message.getBody());
            final JSONObject obj = new JSONObject(message.getBody());
            commitService.createCommit(
                    obj.getLong("branchId"), obj.getString("message"), obj.getString("folderId")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

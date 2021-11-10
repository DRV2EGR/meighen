package ru.pominki.commiter.sevice.consumer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.model.KafkaMsg;
import ru.pominki.commiter.sevice.BranchService;

@Service
public class BranchesConsumerService {
    @Autowired
    BranchService branchService;

    @KafkaListener(topics = "create_branches", groupId = "message_group_id")
    public void createRepo(KafkaMsg message) {
        try {
            System.out.println(message.getBody());
            final JSONObject obj = new JSONObject(message.getBody());
            branchService.createBranch(
                    obj.getString("name"), obj.getLong("owner"), obj.getLong("repoId")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "delete_branches", groupId = "message_group_id")
    public void deleteRepo(KafkaMsg message) {
        try {
            System.out.println(message.getBody());
            final JSONObject obj = new JSONObject(message.getBody());
            branchService.deleteBranch(obj.getLong("branchId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ru.meighgen.commiter.sevice.consumer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.meighgen.commiter.sevice.BranchService;
import ru.meighgen.commiter.model.KafkaMsg;

/**
 * The type Branches consumer service.
 */
@Service
public class BranchesConsumerService {
    /**
     * The Branch service.
     */
    @Autowired
    BranchService branchService;

    /**
     * Create repo.
     *
     * @param message the message
     */
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

    /**
     * Delete repo.
     *
     * @param message the message
     */
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

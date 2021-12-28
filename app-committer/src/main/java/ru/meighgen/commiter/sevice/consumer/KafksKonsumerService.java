package ru.meighgen.commiter.sevice.consumer;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.meighgen.commiter.sevice.RepositoryService;
import ru.meighgen.commiter.model.KafkaMsg;

/**
 * The type Kafks konsumer service.
 */
@Service
public class KafksKonsumerService {
    /**
     * The Repository service.
     */
    @Autowired
    RepositoryService repositoryService;

    /**
     * Consume.
     *
     * @param message the message
     * @throws JsonProcessingException the json processing exception
     */
    @KafkaListener(topics = "messages", groupId = "message_group_id")
    public void consume(KafkaMsg message) throws JsonProcessingException {
        System.out.println("Consuming the message: " + message);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(message.getBody(), Map.class);
        // it works
        //Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
        System.out.println(map);

        System.out.println("CommitId = " + map.get("commitId"));
        System.out.println("FolderId = " + map.get("folderId"));
        System.out.println("msg = " + map.get("messsage"));

    }

    /**
     * Create repo.
     *
     * @param message the message
     */
    @KafkaListener(topics = "create_repos", groupId = "message_group_id")
    public void createRepo(KafkaMsg message) {
        try {
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, String> map = mapper.readValue(message.getBody(), Map.class);
//            System.out.println(map.get("owner").toString());
            final JSONObject obj = new JSONObject(message.getBody());

            repositoryService.createRepo(obj.getString("name"), obj.getLong("owner"), obj.getString("folderId"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete repo.
     *
     * @param message the message
     */
    @KafkaListener(topics = "delete_repos", groupId = "message_group_id")
    public void deleteRepo(KafkaMsg message) {
        try {
            System.out.println(message.getBody());
            final JSONObject obj = new JSONObject(message.getBody());
            repositoryService.deleteRepo(obj.getLong("repoId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ru.pominki.commiter.sevice.consumer;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.pominki.commiter.model.KafkaMsg;
import ru.pominki.commiter.sevice.RepositoryService;

@Service
public class KafksKonsumerService {
    @Autowired
    RepositoryService repositoryService;

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

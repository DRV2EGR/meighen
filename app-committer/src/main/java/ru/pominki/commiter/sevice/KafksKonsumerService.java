package ru.pominki.commiter.sevice;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.model.KafkaMsg;

@Service
public class KafksKonsumerService {

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
}

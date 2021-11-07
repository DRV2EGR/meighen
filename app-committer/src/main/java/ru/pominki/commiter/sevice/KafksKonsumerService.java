package ru.pominki.commiter.sevice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.pominki.commiter.model.KafkaMsg;

@Service
public class KafksKonsumerService {

    @KafkaListener(topics = "messages", groupId = "message_group_id")
    public void consume(KafkaMsg message){
        System.out.println("Consuming the message: " + message);
    }
}

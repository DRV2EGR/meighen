package ru.meighgen.presenter.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Kafka msg.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMsg implements Serializable {
    private String body;
}

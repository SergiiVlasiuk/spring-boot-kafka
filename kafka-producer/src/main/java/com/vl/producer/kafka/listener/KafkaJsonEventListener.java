package com.vl.producer.kafka.listener;

import com.vl.producer.model.MyModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "KafkaJsonEventListener")
@Profile("experimental-json")
public class KafkaJsonEventListener {

    // it will work correctly consuming only JSON payload (if JsonDeserializer configured)
    @KafkaListener(topics = {"${kafka-conf.demo-topic}"})
    void listenEvents(@Payload MyModel message) {
        log.info("received [MyModel] message: {}", message);
    }
}

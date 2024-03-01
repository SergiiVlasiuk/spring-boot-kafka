package com.vl.producer.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "KafkaStringEventListener")
@Profile("experimental-string")
public class KafkaStringEventListener {

    @KafkaListener(topics = {"${kafka-conf.demo-topic}"})
    void listenMessages(@Payload String message) {
        log.info("received [String] message: {}", message);
    }
}

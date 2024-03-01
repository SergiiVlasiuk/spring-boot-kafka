package com.vl.producer.kafka.producer;

import com.vl.producer.configuration.KafkaConfigurationModel;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@Profile("experimental-string")
public class KafkaStringProducer {
    private final KafkaConfigurationModel kafkaTopics;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostConstruct
    void post() {
        kafkaTemplate.setProducerListener(new ActionProducerListener<>());
    }

    @EventListener
    public void publish(String message){
        log.info("publishing [String] message: {}", message);
        kafkaTemplate.send(kafkaTopics.getDemoTopic(), message);
    }
}

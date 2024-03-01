package com.vl.producer.kafka.producer;

import com.vl.model.avro.Report;
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
@Profile("experimental-avro")
public class KafkaAvroProducer {
    private final KafkaConfigurationModel kafkaTopics;
    private final KafkaTemplate<String, Report> kafkaTemplate;

    @PostConstruct
    void post() {
        kafkaTemplate.setProducerListener(new ActionProducerListener<Report>());
    }

    @EventListener
    public void publish(Report message) {
        log.info("publishing [Report] message: {}", message);
        kafkaTemplate.send(kafkaTopics.getDemoTopic(), message);
    }
}

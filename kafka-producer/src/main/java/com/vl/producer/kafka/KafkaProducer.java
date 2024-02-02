package com.vl.producer.kafka;

import com.vl.model.avro.ReportDetails;
import com.vl.producer.configuration.KafkaConfigurationModel;
import com.vl.model.avro2.Template;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaConfigurationModel kafkaTopics;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostConstruct
    void post() {
        kafkaTemplate.setProducerListener(new ActionProducerListener());
    }

    public void publishPrivateCase(Template message){
        kafkaTemplate.send(kafkaTopics.getPrivateCaseTopic(), message);
    }

    public void publishDifferentInformation(Template message){
        kafkaTemplate.send(kafkaTopics.getDifferentInformationTopic(), message);
    }

    public void publishDifferentInformation(ReportDetails message){
        kafkaTemplate.send(kafkaTopics.getReportDetailsTopic(), message);
    }

}

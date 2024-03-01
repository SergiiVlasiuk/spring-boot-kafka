package com.vl.producer.kafka.listener;

import com.vl.model.avro.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "KafkaJsonEventListener")
@Profile("experimental-avro")
public class KafkaAvroEventListener {

    @KafkaListener(topics = {"${kafka-conf.demo-topic}"})
//    @KafkaListener(topics = {"${kafka-conf.report-details-topic}"}, containerFactory = "kafkaListenerContainerFactory")
    void listenReports(@Payload Report message) {
        log.info("received [Report] message: {}", message);
    }
}

package com.vl.producer.kafka;

import com.vl.model.avro.Report;
import com.vl.model.avro.ReportDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaEventsListener {

    // it will work correctly consuming only JSON payload (if JsonDeserializer configured)
    @KafkaListener(topics = {"${kafka-conf.my-model-topic}"})
     void listenEvents(@Payload MyModel message) {
        log.info("received [MyModel] message: {}", message);
    }

    @KafkaListener(topics = {"${kafka-conf.report-details-topic}"})
//    @KafkaListener(topics = {"${kafka-conf.report-details-topic}"}, containerFactory = "kafkaListenerContainerFactory")
     void listenEvents2(@Payload ReportDetails message) {
        log.info("received [ReportDetails] message: {}", message);
    }

    @KafkaListener(topics = {"${kafka-conf.report-topic}"})
//    @KafkaListener(topics = {"${kafka-conf.report-details-topic}"}, containerFactory = "kafkaListenerContainerFactory")
     void listenReports(@Payload Report message) {
        log.info("received [Report] message: {}", message);
    }
}

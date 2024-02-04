package com.vl.producer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
public class ActionProducerListener implements ProducerListener<String, Object> {
    @Override
    public void onSuccess(ProducerRecord<String, Object> producerRecord,
                          RecordMetadata recordMetadata) {
        String topic = producerRecord.topic();
        Integer partition = producerRecord.partition();
        String key = producerRecord.key();
        Object message = producerRecord.value();

        long offset = recordMetadata.offset();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(recordMetadata.timestamp()), ZoneId.systemDefault());

        log.info("[message: {}] Successfully published message. [Topic: {}, partition: {}, key: {}, offset: {}," +
                " dateTime: {}]", message, topic, partition, key, offset, dateTime);
        log.debug("Successfully published message. Message: {}", message);

        ProducerListener.super.onSuccess(producerRecord, recordMetadata);
    }

    @Override
    public void onError(ProducerRecord<String, Object> producerRecord,
                        RecordMetadata recordMetadata,
                        Exception exception) {
        String topic = producerRecord.topic();
        Integer partition = producerRecord.partition();
        String key = producerRecord.key();
        Object message = producerRecord.value();

        long offset = recordMetadata.offset();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(recordMetadata.timestamp()),
                ZoneId.systemDefault());

        log.error("[message: {}] Error sending message. Topic: {}, partition: {}, key: {}, offset: {}," +
                        " dateTime: {}, exception message: {}", message, topic, partition, key, offset,
                dateTime, exception.getMessage(), exception);
        ProducerListener.super.onError(producerRecord, recordMetadata, exception);
    }
}

package com.vl.producer.kafka;

import com.vl.model.avro.ReportDetails;
import com.vl.model.avro2.DifferentInformation;
import com.vl.model.avro2.PrivateCase;
import com.vl.model.avro2.Template;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Service
@Slf4j
@AllArgsConstructor
public class ScheduledService {

    private final KafkaProducer kafkaProducer;

    @Timed(value = "my.scheduled.task.time", description = "Time taken to execute scheduled task")
    @Scheduled(fixedRate = 60000 * 5) // 60000 milliseconds = 1 minute
    public void executeTask() {
        log.info("Executing task every 5 minutes...");
        Random random = new Random();
        Template template;
        if (random.nextBoolean()) {
            template = generatePrivateCaseTemplate();
            kafkaProducer.publishPrivateCase(template);
        } else {
            template = generateDifferentInformationTemplate();
            kafkaProducer.publishDifferentInformation(template);
        }
        log.info("generated: {}", template);
    }

    @Timed(value = "my.scheduled.task.time", description = "Time taken to execute scheduled task")
    @Scheduled(fixedRate = 60000 * 2) // 60000 milliseconds = 1 minute
    public void executeTask2() {
        log.info("Executing task every 2 minutes...");
        final ReportDetails message = generateReportDetails();
        kafkaProducer.publishDifferentInformation(message);
        log.info("generated: {}", message);
    }

    private Template generatePrivateCaseTemplate() {
        return Template.<PrivateCase>newBuilder()
                .setName("name" + randomAlphabetic(10))
                .setDescription("description" + randomAlphabetic(10))
                .setDifferent(PrivateCase.newBuilder()
                        .setId(Long.parseLong(randomNumeric(12)))
                        .setDescription("description" + randomAlphabetic(10))
                        .build())
                .build();
    }

    private Template generateDifferentInformationTemplate() {
        return Template.<DifferentInformation>newBuilder()
                .setName("name" + randomAlphabetic(10))
                .setDescription("description" + randomAlphabetic(10))
                .setDifferent(DifferentInformation.newBuilder()
                        .setTitle("title" + randomAlphabetic(10))
                        .setArticle("description" + randomAlphabetic(10))
                        .build())
                .build();

    }

    private ReportDetails generateReportDetails() {
        return ReportDetails.newBuilder()
                .setDetailName("name" + randomAlphabetic(10))
                .setDetailId("id-" + randomNumeric(10))
                .build();

    }
}

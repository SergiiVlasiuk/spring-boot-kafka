package com.vl.producer.kafka;

import com.vl.model.avro.*;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Service
@Slf4j
@AllArgsConstructor
public class ScheduledService {

    private final KafkaProducer kafkaProducer;

    @Timed(value = "my.scheduled.task.time", description = "Time taken to execute scheduled task")
//    @Scheduled(fixedRate = 60000 * 1) // 60000 milliseconds = 1 minute
    public void executeTask() {
        log.info("[Template] Executing task every 1 minutes...");
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
    @Scheduled(fixedRate = 60000 * 1) // 60000 milliseconds = 1 minute
    public void executeTask2() {
        log.info("[ReportDetails] Executing task every 1 minute...");
        final ReportDetails message = generateReportDetails();
        kafkaProducer.publishReportDetails(message);
        log.info("generated: {}", message);
    }
    @Timed(value = "my.scheduled.task.time", description = "Time taken to execute scheduled task")
//    @Scheduled(fixedRate = 60000 * 1) // 60000 milliseconds = 1 minute
    public void executeTask3() {
        log.info("[MyModel] Executing task every 1 minute...");
        final MyModel message = MyModel.builder()
                .id(UUID.randomUUID())
                .name("name-" + randomAlphabetic(10))
                .description("description-" + randomAlphabetic(10))
                .build();
        kafkaProducer.publishMyModel(message);
        log.info("generated: {}", message);
    }

    @Timed(value = "my.scheduled.task.time", description = "Time taken to execute scheduled task")
    @Scheduled(fixedRate = 60000 * 1) // 60000 milliseconds = 1 minute
    public void executeTask4() {
        log.info("[MyModel] Executing task every 1 minute...");
        final Report message = Report.newBuilder()
                .setReportId(UUID.randomUUID().toString())
                .setEmployee(Employee.newBuilder()
                        .setEmployeeId(UUID.randomUUID().toString())
                        .setDepartment(Integer.parseInt(randomNumeric(3)))
                        .setEmployeeName("EmployeeName-" + randomAlphabetic(4))
                        .setPosition("Position-" + randomAlphabetic(3))
                        .build())
                .setDetails(List.of(
                        ReportDetails.newBuilder()
                                .setDetailId("DetailId-" + randomAlphabetic(7))
                                .setDetailName("DetailName-" + randomAlphabetic(4))
                        .build())
                )
                .build();
        kafkaProducer.publishReport(message);
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

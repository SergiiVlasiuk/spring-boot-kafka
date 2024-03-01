package com.vl.producer.scheduling;

import com.vl.producer.messagefactory.MessageFactory;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "ScheduledService")
@AllArgsConstructor
public class ScheduledService {

    private final MessageFactory messageFactory;
    private final ApplicationEventPublisher publisher;

    @Timed(value = "my.scheduled.task.time", description = "Time taken to execute scheduled task")
    @Scheduled(cron = "${app.cron}")
    public void executeCronTask() {
        log.info("triggered cron");
        final Object message = messageFactory.generateMessage();
        publisher.publishEvent(message);
        log.info("generated: {}", message);
    }
}

package com.vl.demo.streaming.configuration;

import com.vl.model.avro.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Function;

@Configuration
@Slf4j
public class ReportStreaming {

    @Bean
    Function<Message<Report>, Message<Report>> report() {
        return data -> {
            log.info("received in stream [Report]: {}", data.getPayload());
            return data;
        };
    }
//    @Bean
//    Function<KStream<String, Report>, KStream<String, Report>> report() {
//        return input -> input.mapValues(report -> {
//            log.info("received in stream [Report] data: {}", report);
//            return report;
//        });
//    }
}

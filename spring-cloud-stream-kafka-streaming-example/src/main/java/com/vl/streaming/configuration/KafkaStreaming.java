package com.vl.streaming.configuration;

import com.vl.model.avro.Report;
import com.vl.model.avro.ReportDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Slf4j
public class KafkaStreaming {

    @Bean
    Function<ReportDetails, ReportDetails> reportDetails() {
        return data -> {
            log.info("received in stream [ReportDetail]: {}", data);
            return data;
        };
    }

    @Bean
    Function<Report, Report> report() {
        return data -> {
            log.info("received in stream [Report]: {}", data);
            return data;
        };
    }
}

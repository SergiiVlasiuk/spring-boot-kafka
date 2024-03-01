package com.vl.producer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka-conf")
@Data
public class KafkaConfigurationModel {

    private String demoTopic;
}

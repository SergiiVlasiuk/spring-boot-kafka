package com.vl.producer.health;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterOptions;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

@Component
public class KafkaHealthIndicator implements HealthIndicator {

    private final AdminClient adminClient;

    public KafkaHealthIndicator(KafkaProperties kafkaProperties) {
        this.adminClient = AdminClient.create(kafkaProperties.buildAdminProperties());
    }

    @Override
    public Health health() {
        try {
            DescribeClusterResult clusterResult = adminClient.describeCluster(new DescribeClusterOptions().timeoutMs(5000));
            String clusterId = clusterResult.clusterId().get();
            int nodeCount = clusterResult.nodes().get().size();
            if (clusterId != null && nodeCount > 0) {
                return Health.up()
                        .withDetail("clusterId", clusterId)
                        .withDetail("nodeCount", nodeCount)
                        .build();
            } else {
                return Health.down()
                        .withDetail("message", "Failed to connect to Kafka cluster")
                        .build();
            }
        } catch (Exception e) {
            return Health.down(e)
                    .withDetail("message", "Error connecting to Kafka cluster: " + e.getMessage())
                    .build();
        }
    }
}

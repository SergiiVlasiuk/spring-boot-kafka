package com.vl.producer.health;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterOptions;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1)
@TestPropertySource(locations = "classpath:test.properties")
public class KafkaHealthIndicatorTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private KafkaHealthIndicator kafkaHealthIndicator;
    @MockBean
    private AdminClient adminClient;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    DescribeClusterResult describeCluster;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(kafkaHealthIndicator, "adminClient", adminClient);
    }

    @Test
    public void testHealthCheckEndpoint() throws Exception {
        when(adminClient.describeCluster(any(DescribeClusterOptions.class))).thenReturn(describeCluster);
        String clusterId = randomAlphabetic(5);
        Integer nodeCount = Integer.parseInt(randomNumeric(1));
        when(describeCluster.clusterId().get()).thenReturn(clusterId);
        when(describeCluster.nodes().get().size()).thenReturn(nodeCount);
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("UP"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.components.kafka.status").value("UP"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.components.kafka.details.clusterId").value(clusterId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.components.kafka.details.nodeCount").value(nodeCount));
    }
}

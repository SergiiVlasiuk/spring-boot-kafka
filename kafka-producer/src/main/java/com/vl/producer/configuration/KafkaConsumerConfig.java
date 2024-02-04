//package com.vl.producer.configuration;
//
//import com.vl.model.avro.ReportDetails;
//import io.confluent.kafka.serializers.KafkaAvroDeserializer;
//import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
// // this configuration is correct one, but doesn't required because of yml configuration.
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Value("${kafka-conf.server}")
//    private String bootstrapAddress;
//    @Value("${kafka-conf.group-id}")
//    private String groupId;
//    @Value("${kafka-conf.schema-registry}")
//    private String schemaRegistry;
//
//    @Bean
//    public ConsumerFactory<String, ReportDetails> consumerFactory() {
//        Map<String, Object> consumerProps = new HashMap<>();
//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
//        consumerProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
//        consumerProps.put("schema.registry.url", schemaRegistry);
//
//        return new DefaultKafkaConsumerFactory<>(consumerProps);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, ReportDetails> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, ReportDetails> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//}
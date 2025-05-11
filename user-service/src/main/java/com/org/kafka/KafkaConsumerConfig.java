package com.org.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConsumerConfig {
    @Value("${kafka.topic.transaction.name}")
    private String topicName;

    @Value("${kafka.topic.transaction.partitions}")
    private Integer partitions;

    @Value("${kafka.topic.transaction.replicas}")
    private Integer replicas;


    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(topicName)
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }
}

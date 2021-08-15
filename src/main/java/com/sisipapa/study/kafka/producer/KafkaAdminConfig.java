package com.sisipapa.study.kafka.producer;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaAdmin 객체 초기화하고 어플리케이션 로딩 시 topicName에 해당하는 Topic을 등록한다. topicName이름의 Topic이 등록되어 있으면 아무런 동작을 하지 않는다.
 *
 * public NewTopic(String name, int numPartitions, short replicationFactor) {
 *         this(name, Optional.of(numPartitions), Optional.of(replicationFactor));
 *     }
 *
 * ./bin/kafka-topics.sh --create \
 *     --replication-factor 3 \
 *     --partitions 3 \
 *     --topic app-push-topic \
 *     --zookeeper  localhost:2181
 */
@Configuration
public class KafkaAdminConfig {

    @Value("${kafka.producer.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.producer.topic.name}")
    private String topicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

//    @Bean
//    public NewTopic newTopic() {
//        return new NewTopic(topicName, 3, (short) 3);
//    }
}

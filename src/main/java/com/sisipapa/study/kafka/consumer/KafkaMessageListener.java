package com.sisipapa.study.kafka.consumer;

import com.sisipapa.study.kafka.common.PushDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageListener {

    @KafkaListener(topics = "${kafka.consumer.topic.name}"
            , groupId = "${kafka.consumer.topic.group.name}"
            , containerFactory = "pushEntityKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload PushDto pushDto,
                                  @Headers MessageHeaders messageHeaders) {

        log.info("Received Message: {}, headers: {}", pushDto.toString() ,messageHeaders);
    }
}

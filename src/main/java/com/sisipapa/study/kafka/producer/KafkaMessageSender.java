package com.sisipapa.study.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 *  kafkaTemplate.send(message); 는 리턴타입은 ListenableFuture 이다. ListenableFuture을 통해 Producer의 메세지 송신여부, 현재 보낸 파티션의 offset값을 확인할 수 있다.
 */
@Slf4j
@Component
public class KafkaMessageSender {

    @Autowired
    private KafkaTemplate<String, PushDto> kafkaTemplate;

    @Value("${kafka.producer.topic.name}")
    private String topicName;

    public void send(PushDto pushDto) {

        Message<PushDto> message = MessageBuilder
                .withPayload(pushDto)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        ListenableFuture<SendResult<String, PushDto>> future =
                kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, PushDto>>() {

            @Override
            public void onSuccess(SendResult<String, PushDto> stringObjectSendResult) {
                log.info("Sent message=[" + stringObjectSendResult.getProducerRecord().value().toString() + "] with offset=[" + stringObjectSendResult.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable e) {
                log.info("KafkaMessageSender onFailure : {}" + e.getMessage());
            }
        });
    }

}

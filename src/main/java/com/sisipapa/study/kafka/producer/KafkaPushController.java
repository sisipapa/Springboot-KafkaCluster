package com.sisipapa.study.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaPushController {

    @Autowired
    private KafkaMessageSender kafkaMessageSender;

    @PostMapping("/push")
    public String push(@RequestBody PushDto pushDto) {
        kafkaMessageSender.send(pushDto);
        return "success";
    }

}

package com.sisipapa.study.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushDto {

    private String token;
    private String message;

}

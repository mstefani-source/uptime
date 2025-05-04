package com.zmey.uptime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReadTargetDto {
    private Long id;
    private Long customerId;
    private String url;
}

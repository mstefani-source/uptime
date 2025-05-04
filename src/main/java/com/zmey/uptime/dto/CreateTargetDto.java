package com.zmey.uptime.dto;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CreateTargetDto {

    private Long customerId;
    private String url;
    private String name;
    private String description;
}

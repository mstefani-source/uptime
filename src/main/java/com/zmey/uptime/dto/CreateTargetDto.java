package com.zmey.uptime.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateTargetDto {

    private Long customerId;
    private String url;
    private String name;
    private String description;
}

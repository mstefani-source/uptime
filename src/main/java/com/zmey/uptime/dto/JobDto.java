package com.zmey.uptime.dto;

import com.zmey.uptime.entities.enums.Protocol;

import lombok.Data;

@Data
public class JobDto {
    private String name;
    private String group;
    private String destination;
    private Protocol type;
    private Integer frequency;
}

package com.zmey.uptime.dto;

import java.io.Serializable;

import com.zmey.uptime.entities.enums.Protocol;

import lombok.Data;

@Data
public class TargetDto implements Serializable {
    private Long customerId;
    private String url;
    private String name;
    private String description;
    private Boolean active;
    private Protocol protocol;
    private Integer frequency;
}

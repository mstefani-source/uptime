package com.zmey.uptime.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TargetDto {

    private Long customerId;
    private String url;
    private String name;
    private String description;
    private Boolean active;
}

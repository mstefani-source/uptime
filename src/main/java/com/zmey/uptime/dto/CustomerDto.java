package com.zmey.uptime.dto;

import com.zmey.uptime.entities.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role = Role.ROLE_USER;
}

package com.zmey.uptime.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.zmey.uptime.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "customers")
public class Customer  {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

}

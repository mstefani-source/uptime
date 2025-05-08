package com.zmey.uptime.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank
    private String name;
}

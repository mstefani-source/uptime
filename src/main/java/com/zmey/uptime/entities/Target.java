package com.zmey.uptime.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "targets")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter

public class Target implements BaseTarget {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String customer;

    @Column(unique = true)
    @NotNull
    private String url;

    @NotBlank
    private String name;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @LastModifiedDate
    private LocalDate updatedAt;

    @NotNull
    @CreatedDate
    private LocalDate createdAt;

}

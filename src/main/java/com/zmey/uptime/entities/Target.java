package com.zmey.uptime.entities;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    public Target(String customer, String url, String name, String description) {
        this.customer = customer;
        this.url = url;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Target newTarget = (Target) obj;

        return (customer == newTarget.customer || customer != null && customer.equals(newTarget.customer))
                && (url == newTarget.url || url != null && url.equals(newTarget.url))
                && (name == newTarget.name || name != null && name.equals(newTarget.name))
                && (description == newTarget.description
                        || description != null && description.equals(newTarget.description));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", customer='" + customer + '\'' + ", url='" + url + '\'' + ", name='" + name + '\''
                + ", description='" + description + '\'' + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt
                + '}';
    }

}

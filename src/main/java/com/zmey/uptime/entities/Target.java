package com.zmey.uptime.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "targets")
@EntityListeners(AuditingEntityListener.class)
public class Target implements BaseTarget {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // @NotBlank
    @ManyToMany
    private Customer customer;

    @Column(unique = true)
    @NotNull
    private String url;

    // @NotBlank
    private String name;

    // @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    public Target(Customer customer, String url, String name, String description) {
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
                && ((name == null ? newTarget.name == null : name.equals(newTarget.name)) || name != null && name.equals(newTarget.name))
                && ((description == null ? newTarget.description == null : description.equals(newTarget.description))
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

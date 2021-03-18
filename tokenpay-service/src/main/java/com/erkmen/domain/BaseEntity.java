package com.erkmen.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime createdDateTime;

    @Column
    private LocalDateTime updatedDateTime;

    @PrePersist
    private void prePersist() {
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedDateTime = LocalDateTime.now();
    }
}

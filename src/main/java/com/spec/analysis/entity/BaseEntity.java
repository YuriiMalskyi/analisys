package com.spec.analysis.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "created_by", insertable = false, updatable = false)
    private String createdBy;

    @NotNull
    @Column(name = "edited_at", insertable = false, updatable = false)
    private LocalDateTime editedAt;

    @Column(name = "modified_by", insertable = false, updatable = false)
    private String editedBy;

    @PrePersist
    private void prePersist(){
        System.out.println("PRE PERSIST IS WORKING");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.editedAt = LocalDateTime.now();
    }

}

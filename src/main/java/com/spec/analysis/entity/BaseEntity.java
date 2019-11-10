package com.spec.analysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime editedAt;

    @Column(name = "modified_by", insertable = false, updatable = false)
    private String editedBy;

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.createdBy = getAuthenticatedCustomerUserName();
    }

    @PreUpdate
    private void preUpdate(){
        this.editedAt = LocalDateTime.now();
        this.editedBy = getAuthenticatedCustomerUserName();
    }


    @Transient
    private String getAuthenticatedCustomerUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName().equalsIgnoreCase("anonymousUser")) {
            return "";
        }

        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
}

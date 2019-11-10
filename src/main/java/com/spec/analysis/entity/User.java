package com.spec.analysis.entity;

import com.spec.analysis.enums.Authorities;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class User extends BaseEntity{

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Authorities authority;

}

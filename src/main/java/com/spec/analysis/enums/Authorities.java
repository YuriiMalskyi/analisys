package com.spec.analysis.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_LECTURER("ROLE_LECTURER");

    private String value;

    Authorities(String value) {
        this.value = value;
    }

    public static Authorities from(String value) {
        return Authorities.valueOf(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return value;
    }

}

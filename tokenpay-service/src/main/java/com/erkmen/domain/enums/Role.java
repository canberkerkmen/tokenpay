package com.erkmen.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    NONE, ADMIN, USER;

    public String getAuthority() {
        return name();

    }

}

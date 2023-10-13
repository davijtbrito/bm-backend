package com.bm.businessmanagement.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    
    ADMIN("ROLE_ADMIN"),    
    USER("ROLE_USER"),
    NONE("ROLE_NONE");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static Role fromValue(String authority) {

        for (Role enumConstant : Role.values()) {
            if (enumConstant.authority == authority) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + authority);
    }
}

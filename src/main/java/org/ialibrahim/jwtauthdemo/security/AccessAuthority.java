package org.ialibrahim.jwtauthdemo.security;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccessAuthority implements DemoAuthority {
    ADMIN, CUSTOMER;

    @Override
    public String getAuthority() {

        return name();
    }

    @JsonCreator
    public static AccessAuthority fromValue(String value) {

        for (AccessAuthority b : AccessAuthority.values()) {
            if (b.name().equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}


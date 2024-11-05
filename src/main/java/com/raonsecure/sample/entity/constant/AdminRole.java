package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdminRole {
    SUPER_ADMIN("Super Admin"),
    ADMIN("Admin"),
    ;

    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}

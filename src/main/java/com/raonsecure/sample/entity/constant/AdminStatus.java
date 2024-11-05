package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdminStatus {
    ACTIVE("Activo"),
    DELETED("Eliminado")
    ;

    private final String displayName;
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}

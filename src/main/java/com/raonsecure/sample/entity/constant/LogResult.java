package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LogResult {
    SUCCESS("Éxito"),
    FAIL("Fallo"),
    PENDING("Pendiente"),
    ;

    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}

package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    ACTIVE("Activo"),
    SUSPENDED("Suspendido"),
    DELETED("Eliminado")
    ;

    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    @JsonCreator
    public static UserStatus forValue(String value) {
        for (UserStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid UserStatus value: " + value);
    }
}

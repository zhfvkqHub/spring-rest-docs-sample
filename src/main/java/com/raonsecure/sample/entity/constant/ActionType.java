package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActionType {
    READ("Leer"),
    CREATE("Crear"),
    UPDATE("Actualizar"),
    DELETE("Eliminar")
    ;

    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}

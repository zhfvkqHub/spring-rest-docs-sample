package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public enum Menu {
    LOGIN("Iniciar sesión"),
    LOGOUT("Cerrar sesión"),
    ADMIN("Administrador"),
    ADMIN_LOG("Registro de Admin"),
    PROFILE("Configuración"),
    NOTICE("Notificación"),
    USER("Usuarios"),
    SESSION("Sesión"),
    SERVICE_LOG("Registro de Servicio de Wallet"),
    GAUDI_API("GAUDI"),
    EXTERNAL_API("API externa")
    ;

    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    @JsonCreator
    public static Menu forValue(String value) {
        for (Menu menu : values()) {
            if (menu.name().equalsIgnoreCase(value)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("Invalid Menu value: " + value);
    }

    public static List<Map<String, String>> getMenuList() {
        return List.of(Menu.values()).stream()
                .map(menu -> Map.of(
                        "code", menu.name(),
                        "value", menu.getDisplayName()
                ))
                .toList();
    }
}

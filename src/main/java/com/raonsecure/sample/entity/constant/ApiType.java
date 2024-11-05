package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ApiType {
    SIGNUP("Registrarse"),
    CHECK_SIGNUP("Verificar Registro"),
    LOGIN("Iniciar sesión"),
    CHECK_LOGIN("Verificar Inicio de Sesión"),
    LOGOUT("Cerrar sesión"),
    WITHDRAW("Retirar"),
    SIGN_EDOC("Firmar eDoc"),
    CHECK_SIGN_EDOC("Verificar Firma eDoc de Gaudi"),
    GAUDI_AUTHENTICATION("Autenticación de Gaudi"),
    GAUDI_NOTIFICATION("Notificación de Gaudi"),
    GAUDI_SIGN_EDOC("Firmar eDoc de Gaudi"),
    REQUEST_EDOC("Solicitar eDoc"),
    AGENCY_EDOC("eDoc de Agencia"),
    GET_NOTIFICATION("Obtener Notificación"),
    GET_AGENCY_INFO("Obtener Información de la Agencia"),
    ;

    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    public static ApiType forValue(String value) {
        for (ApiType apiType : ApiType.values()) {
            if (apiType.getDisplayName().equals(value)) {
                return apiType;
            }
        }
        throw new IllegalArgumentException("Invalid ApiType value: " + value);
    }

    public static List<Map<String, String>> getApiTypeList() {
        return List.of(ApiType.values()).stream()
                .map(apiType -> Map.of(
                        "code", apiType.name(),
                        "value", apiType.getDisplayName()
                ))
                .toList();
    }
}

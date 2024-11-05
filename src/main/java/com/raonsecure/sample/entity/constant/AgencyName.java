package com.raonsecure.sample.entity.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgencyName {
    GAUDI("0000", "GAUDI"),
    EWALLET("9999", "E-Wallet"),
    FINANCE("0001", "Finanza"),
    POLICE("0002", "Police"),
    HEALTHY("0003", "Healthy"),
    ;

    private final String agencyCode;
    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static AgencyName forValue(String value) {
        for (AgencyName agencyName : values()) {
            if (agencyName.name().equalsIgnoreCase(value)) {
                return agencyName;
            }
        }
        throw new IllegalArgumentException("Invalid AgencyName value: " + value);
    }
}

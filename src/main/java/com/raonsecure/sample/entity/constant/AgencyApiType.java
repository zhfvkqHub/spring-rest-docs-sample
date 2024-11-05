package com.raonsecure.sample.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgencyApiType {
    AUTH("AUTH", "User Authentication Api"),
    SIGN("SIGN", "Document Sign Api"),
    EDOC("EDOC", "Document Get Api"),
    ;

    private final String value;
    private final String displayName;
}

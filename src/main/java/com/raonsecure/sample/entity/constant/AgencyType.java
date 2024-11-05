package com.raonsecure.sample.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgencyType {
    GAUDI("GAUDI", "Gaudi Agency"),
    EWALLET("EWALLET", "eWallet Agency"),
    THIRD_PARTY("THIRD_PARTY", "3rd Party Agency"),
    ;

    private final String value;
    private final String displayName;
}

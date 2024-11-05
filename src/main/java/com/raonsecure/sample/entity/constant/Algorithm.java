package com.raonsecure.sample.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Algorithm {
    SHA256("Sha256", 1),
    SHA384("Sha384", 2),
    SHA512("Sha512", 3);

    private final String algorithmName;
    private final int algorithmType;

}

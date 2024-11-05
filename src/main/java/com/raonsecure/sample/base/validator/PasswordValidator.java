package com.raonsecure.sample.base.validator;

import com.raonsecure.sample.annotation.valid.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 공백 포함 불가
        return !value.contains(" ");
    }
}

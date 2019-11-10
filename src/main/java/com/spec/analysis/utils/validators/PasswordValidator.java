package com.spec.analysis.utils.validators;


import com.spec.analysis.utils.validators.constants.ValidationConstants;
import com.spec.analysis.utils.validators.constants.ValidationEnums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (Objects.isNull(password)) {
            isValid = parseErrors(false, ValidationEnums.NULL, constraintValidatorContext);
        } else {
            if (password.length() < 6) {
                isValid = parseErrors(false, ValidationEnums.LENGTH, constraintValidatorContext);
            }
            if (!ValidationConstants.SMALL_CHARACTERS.matcher(password).find()) {
                isValid = parseErrors(isValid, ValidationEnums.SMALL_CHARACTERS, constraintValidatorContext);
            }
            if (!ValidationConstants.BIG_CHARACTERS.matcher(password).find()) {
                isValid = parseErrors(isValid, ValidationEnums.BIG_CHARACTERS, constraintValidatorContext);
            }
            if (!ValidationConstants.DIGITS.matcher(password).find()) {
                isValid = parseErrors(isValid, ValidationEnums.DIGITS, constraintValidatorContext);
            }
        }

        return isValid;
    }

    private boolean parseErrors(boolean isValid, ValidationEnums invalid,
                                ConstraintValidatorContext constraintValidatorContext) {
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
        }
        switch (invalid) {

            case NULL: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Password can not be null!")
                        .addConstraintViolation();
            }
            break;

            case LENGTH: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Password should contain from 8 to 60 characters!")
                        .addConstraintViolation();
            }
            break;

            case DIGITS: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Password should contain digits!")
                        .addConstraintViolation();
            }
            break;

            case SMALL_CHARACTERS: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Password should contain small letters!")
                        .addConstraintViolation();
            }
            break;

            default: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Password should contain big letters!")
                        .addConstraintViolation();
            }
        }
        return false;
    }

    private void setDefaultValidationFailure(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Password validation failed!")
                .addConstraintViolation();

    }

}

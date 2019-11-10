package com.spec.analysis.utils.validators;

import com.spec.analysis.utils.validators.constants.ValidationConstants;
import com.spec.analysis.utils.validators.constants.ValidationEnums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    private String fieldName = "";

    @Override
    public void initialize(Username constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (Objects.isNull(name)) {
            isValid = parseErrors(false, ValidationEnums.NULL, constraintValidatorContext);
        } else {
            if (name.length() < 1 || name.length() > 50) {
                isValid = parseErrors(false, ValidationEnums.LENGTH, constraintValidatorContext);
            }
            if (ValidationConstants.DIGITS.matcher(name).find()) {
                isValid = parseErrors(isValid, ValidationEnums.DIGITS, constraintValidatorContext);
            }
            if (ValidationConstants.SPECIAL_CHARACTERS.matcher(name).find()) {
                isValid = parseErrors(isValid, ValidationEnums.SPECIAL_CHARACTERS, constraintValidatorContext);
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
                        .buildConstraintViolationWithTemplate(String.format("%s can not be null!", fieldName))
                        .addConstraintViolation();
            }
            break;

            case LENGTH: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(
                                String.format("%s should contain from 1 to 50 character!", fieldName))
                        .addConstraintViolation();
            }
            break;

            case SPECIAL_CHARACTERS: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(
                                String.format("%s can not contain special characters!", fieldName))
                        .addConstraintViolation();
            }
            break;

            default: {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(String.format("%s can not contain digits!", fieldName))
                        .addConstraintViolation();
            }
            break;
        }
        return false;
    }

}
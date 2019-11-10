package com.spec.analysis.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    /**
     * Method for returning assertion error message.
     *
     * @return default assertion error.
     */
    String message() default "Password validation failed!";

    /**
     * Method which is required for annotation validation to work.
     *
     * @return objects array.
     */
    Class<?>[] groups() default {};

    /**
     * Method which is required for annotation validation to work.
     *
     * @return payload's subclass objects.
     */
    Class<? extends Payload>[] payload() default {};

}

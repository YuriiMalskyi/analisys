package com.spec.analysis.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = UsernameValidator.class)
public @interface Username {

    /**
     * Method for returning assertion error message.
     *
     * @return default assertion error.
     */
    String message() default "Username field validation failed.";

    /**
     * Set name of our field.
     *
     * @return field name in validation error.
     */
    String fieldName() default "Username field.";

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

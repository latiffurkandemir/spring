package com.furkan.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidatior.class)
@Target({ElementType.METHOD,ElementType.FIELD})//where we can apply this annotation
@Retention(RetentionPolicy.RUNTIME)//retain this annotation in the Java class file. Process it at runtime
public @interface CourseCode {//creating a custom annotation for validation

    //define default course code
    public String value() default "BAU";

    //define default error message
    public String message() default "must start with BAU";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};
}

package com.furkan.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class CourseCodeConstraintValidatior implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();//BAU @CourseCode(value="BAU", message="must start with BAU")
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {//context is for additional error messages
        //code is an HTML form data entered by user(course code by user)
        boolean result;

        if (code != null) {
            result = code.startsWith(coursePrefix);
        } else {
            result = true;
        }
        return result;
    }
}

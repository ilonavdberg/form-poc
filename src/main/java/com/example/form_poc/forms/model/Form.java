package com.example.form_poc.forms.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

public interface Form {
    default void validateForm(Validator validator) {
        Set<ConstraintViolation<Object>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Form validation failed: " + violations);
        }
    }
}

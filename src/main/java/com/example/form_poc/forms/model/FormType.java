package com.example.form_poc.forms.model;

import com.example.form_poc.formData.model.FormData;

import java.util.function.Function;

public enum FormType {
    A(FormA::from),
    B(FormB::from);

    private final Function<FormData, Form> creator;

    FormType(Function<FormData, Form> creator) {
        this.creator = creator;
    }

    public Form create(FormData data) {
        return creator.apply(data);
    }
}

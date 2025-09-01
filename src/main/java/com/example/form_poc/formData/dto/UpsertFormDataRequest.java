package com.example.form_poc.formData.dto;

import com.example.form_poc.formData.model.FormData;
import com.example.form_poc.forms.model.FormType;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UpsertFormDataRequest {
    @NotEmpty
    private List<FormType> formTypes;

    private String fieldX;
    private String fieldY;
    private String fieldZ;

    public FormData toEntity() {
        return FormData.builder()
                .formTypes(this.formTypes)
                .fieldX(this.fieldX)
                .fieldY(this.fieldY)
                .fieldZ(this.fieldZ)
                .build();
    }

    public void applyTo(FormData formData) {
        formData.setFormTypes(this.formTypes);
        formData.setFieldX(this.fieldX);
        formData.setFieldY(this.fieldY);
        formData.setFieldZ(this.fieldZ);
    }
}

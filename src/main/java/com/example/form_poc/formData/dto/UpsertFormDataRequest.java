package com.example.form_poc.formData.dto;

import com.example.form_poc.formData.model.FormData;
import com.example.form_poc.forms.model.FormType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class UpsertFormDataRequest {
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
}

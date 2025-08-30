package com.example.form_poc.formData.service;

import com.example.form_poc.formData.dto.UpsertFormDataRequest;
import com.example.form_poc.formData.model.FormData;
import com.example.form_poc.formData.repository.FormDataRepository;
import com.example.form_poc.forms.model.FormType;
import com.example.form_poc.forms.model.Form;
import com.example.form_poc.forms.model.FormA;
import com.example.form_poc.forms.model.FormB;
import com.example.form_poc.forms.repository.FormARepository;
import com.example.form_poc.forms.repository.FormBRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormDataService {

    private final FormDataRepository formDataRepository;
    private final FormARepository formARepository;
    private final FormBRepository formBRepository;
    private final Validator validator;

    @Transactional
    public FormData create(UpsertFormDataRequest request) {
        FormData formData = request.toEntity();
        validateFormData(formData);
        return formDataRepository.save(formData);
    }

    @Transactional
    public FormData update(UUID uuid, UpsertFormDataRequest request) {
        FormData formData = formDataRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("No form data found with UUID: " + uuid));

        updateFormDataFromRequest(request, formData);
        validateFormData(formData);
        return formDataRepository.save(formData);
    }

    @Transactional
    public List<Form> createFormsFromData(UUID formDataUuid, List<FormType> formTypes) {
        FormData formData = formDataRepository.findByUuid(formDataUuid)
                .orElseThrow(() -> new EntityNotFoundException("No form data found with UUID: " + formDataUuid));

        List<Form> forms = new ArrayList<>();
        for (FormType type : formTypes) {
            forms.add(createForm(formData, type));
        }
        return forms;
    }

    private void validateFormData(FormData formData) {
        List<FormType> formTypes = formData.getFormTypes();
        if (formTypes == null || formTypes.isEmpty()) {
            throw new IllegalArgumentException("At least one form type must be selected.");
        }

        formData.getFormTypes().forEach(formType -> {
            Form form = formType.create(formData);
            form.validateForm(validator);
        });
    }

    private Form createForm(FormData formData, FormType formType) {
        if (!formData.getFormTypes().contains(formType)) {
            throw new IllegalStateException(String.format("Cannot create form of type '%s' for FormData with UUID '%s': required data is missing", formType, formData.getUuid()));
        }

        return switch (formType) {
            case FormType.A -> formARepository.save(FormA.from(formData));
            case FormType.B -> formBRepository.save(FormB.from(formData));
        };
    }

    private static void updateFormDataFromRequest(UpsertFormDataRequest request, FormData formData) {
        formData.setFormTypes(request.getFormTypes());
        formData.setFieldX(request.getFieldX());
        formData.setFieldY(request.getFieldY());
        formData.setFieldZ(request.getFieldZ());
    }
}

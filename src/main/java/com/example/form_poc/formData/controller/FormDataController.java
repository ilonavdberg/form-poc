package com.example.form_poc.formData.controller;

import com.example.form_poc.formData.dto.UpsertFormDataRequest;
import com.example.form_poc.formData.model.FormData;
import com.example.form_poc.formData.service.FormDataService;
import com.example.form_poc.forms.model.FormType;
import com.example.form_poc.forms.model.Form;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/form-data")
@RequiredArgsConstructor
public class FormDataController {

    private final FormDataService formDataService;

    @PostMapping
    public ResponseEntity<FormData> create(@RequestBody UpsertFormDataRequest request) {
        FormData result = formDataService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<FormData> update(@PathVariable UUID uuid, @RequestBody UpsertFormDataRequest request) {
        FormData result = formDataService.update(uuid, request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{uuid}/forms")
    public ResponseEntity<Form> createFormFromData(@PathVariable UUID uuid, @RequestParam("type") FormType formType) {
        Form result = formDataService.createFormFromData(uuid, formType);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

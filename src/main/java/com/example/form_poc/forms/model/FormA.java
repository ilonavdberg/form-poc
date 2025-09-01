package com.example.form_poc.forms.model;

import com.example.form_poc.formData.model.FormData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FormA implements Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    @NotNull
    private UUID formDataUuid;

    @NotBlank
    private String fieldX;

    @NotBlank
    private String fieldY;

    public static FormA from(FormData data) {
        return FormA.builder()
                .formDataUuid(data.getUuid())
                .fieldX(data.getFieldX())
                .fieldY(data.getFieldY())
                .build();
    }
}

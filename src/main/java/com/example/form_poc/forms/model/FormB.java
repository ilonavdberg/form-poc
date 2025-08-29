package com.example.form_poc.forms.model;

import com.example.form_poc.formData.model.FormData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FormB implements Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private UUID uuid = UUID.randomUUID();

    private UUID formDataUuid;

    private String fieldX;
    private String fieldZ;

    public static FormB from(FormData data) {
        return FormB.builder()
                .formDataUuid(data.getUuid())
                .fieldX(data.getFieldX())
                .fieldZ(data.getFieldZ())
                .build();
    }
}

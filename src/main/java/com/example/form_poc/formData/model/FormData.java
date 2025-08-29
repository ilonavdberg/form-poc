package com.example.form_poc.formData.model;

import com.example.form_poc.forms.model.FormType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FormData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private UUID uuid = UUID.randomUUID();

    @ElementCollection(targetClass = FormType.class)
    @CollectionTable(
            name = "form_data_types",
            joinColumns = @JoinColumn(name = "form_data_id")
    )
    @Enumerated(EnumType.STRING)
    private List<FormType> formTypes;

    private String fieldX; // common field
    private String fieldY; // FormA specific field
    private String fieldZ; // FormB specific field
}

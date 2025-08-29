package com.example.form_poc.formData.repository;

import com.example.form_poc.formData.model.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, Long> {
    Optional<FormData> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}

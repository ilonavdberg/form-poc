package com.example.form_poc.forms.repository;

import com.example.form_poc.forms.model.FormB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormBRepository extends JpaRepository<FormB, Long> {
}

package com.pm.patientservice.repository;

import com.pm.patientservice.model.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<patient, UUID> {
    boolean existsAllByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);

}

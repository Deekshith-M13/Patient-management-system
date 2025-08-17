package com.pm.patientservice.Service;

import com.pm.patientservice.GlobalExceptinHandler.EmailAlreadyExists;
import com.pm.patientservice.GlobalExceptinHandler.PatientNotFoundException;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.patientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<patientResponseDTO> getPatients() {
        List<patient> patients = patientRepository.findAll();

        List<patientResponseDTO> patientResponseDTOList = patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        return patientResponseDTOList;
    }

    public patientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if (patientRepository.existsAllByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExists(" email already exists");
        }
        patient newpatient = patientRepository.save(PatientMapper.tomodel(patientRequestDTO));

        return PatientMapper.toDTO(newpatient);
    }

    public patientResponseDTO updatepatient(UUID id, PatientRequestDTO patientRequestDTO) {

        patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("id not found"));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExists(" email already exists");
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        patient updatePatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatePatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}

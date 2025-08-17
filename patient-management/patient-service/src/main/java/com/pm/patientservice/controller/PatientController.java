package com.pm.patientservice.controller;

import com.pm.patientservice.Service.PatientService;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.patientResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient",description = "API for Patient management")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<patientResponseDTO>> getPatients() {
        List<patientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<patientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {


        patientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "To update Patient")
    public ResponseEntity<patientResponseDTO> updatePatient(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){

        patientResponseDTO patientResponseDTO = patientService.updatepatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);


    }

    @DeleteMapping("/{id}")
    @Operation(summary = "To delete a Patient")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }


}

package com.czarnecki.clinicservicesystem.patient;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/patients")
class PatientController {
    private final PatientFacade patientFacade;

    PatientController(PatientFacade patientService) {
        this.patientFacade = patientService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_SEE_PATIENTS')")
    ResponseEntity<?> getPatients() {
        return ResponseEntity.ok(patientFacade.getPatients());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CAN_ADD_PATIENTS')")
    ResponseEntity<?> addPatient(@RequestBody @Valid PatientDto request) {
        patientFacade.addPatient(request);
        return ResponseEntity.ok("Patient has been created!");
    }
}

package com.czarnecki.clinicservicesystem.patient;

import jakarta.validation.Valid;
import java.util.List;
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
    private final PatientQueryRepository patientQueryRepository;

    PatientController(final PatientFacade patientService,
        final PatientQueryRepository patientQueryRepository) {
        this.patientFacade = patientService;
        this.patientQueryRepository = patientQueryRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_SEE_PATIENTS')")
    ResponseEntity<List<PatientDto>> getPatients() {
        return ResponseEntity.ok(patientQueryRepository
            .findAll()
            .stream()
            .toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CAN_ADD_PATIENTS')")
    ResponseEntity<String> addPatient(@RequestBody @Valid PatientDto request) {
        patientFacade.addPatient(request);
        return ResponseEntity.ok("Patient has been created");
    }
}

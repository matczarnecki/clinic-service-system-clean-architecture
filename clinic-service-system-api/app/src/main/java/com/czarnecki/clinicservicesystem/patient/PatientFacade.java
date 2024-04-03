package com.czarnecki.clinicservicesystem.patient;

import org.springframework.stereotype.Service;

@Service
public class PatientFacade {
    private final PatientRepository patientRepository;

    PatientFacade(final PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void addPatient(PatientDto request) {
        var patient = new Patient();
        patient.setFirstName(request.firstName());
        patient.setLastName(request.lastName());
        patientRepository.save(patient);
    }
}

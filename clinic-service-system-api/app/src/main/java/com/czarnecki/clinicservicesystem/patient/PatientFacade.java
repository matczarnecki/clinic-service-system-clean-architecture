package com.czarnecki.clinicservicesystem.patient;

import org.springframework.stereotype.Service;

@Service
public class PatientFacade {
    private final PatientRepository patientRepository;
    private final PatientFactory patientFactory;

    PatientFacade(
        final PatientRepository patientRepository,
        final PatientFactory patientFactory) {
        this.patientRepository = patientRepository;
        this.patientFactory = patientFactory;
    }

    public void save(PatientDto request) {
        var patient = patientFactory.from(request);
        patientRepository.save(patient);
    }
}

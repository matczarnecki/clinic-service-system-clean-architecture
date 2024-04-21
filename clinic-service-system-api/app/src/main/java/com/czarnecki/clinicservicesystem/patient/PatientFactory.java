package com.czarnecki.clinicservicesystem.patient;

import com.czarnecki.clinicservicesystem.patient.vo.PatientSnapshot;
import org.springframework.stereotype.Service;

@Service
public class PatientFactory {
    Patient from(final PatientDto request) {
        return Patient.from(
            new PatientSnapshot(
                null,
                request.firstName(),
                request.lastName())
        );

    }
}

package com.czarnecki.clinicservicesystem.patient;

import java.util.Optional;

interface PatientRepository {
    Optional<Patient> findById(Integer id);

    Patient save(Patient entity);
}

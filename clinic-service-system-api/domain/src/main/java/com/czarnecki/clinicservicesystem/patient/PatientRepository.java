package com.czarnecki.clinicservicesystem.patient;

import java.util.Optional;
import java.util.Set;

interface PatientRepository {
  Optional<Patient> findById(Integer id);

  Set<Patient> findAll();

  Patient save(Patient entity);
}

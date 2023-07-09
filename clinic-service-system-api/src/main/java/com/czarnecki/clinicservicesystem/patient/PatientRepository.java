package com.czarnecki.clinicservicesystem.patient;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;

interface PatientRepository extends CrudRepository<Patient, Integer> {
  Set<Patient> findAll();
}

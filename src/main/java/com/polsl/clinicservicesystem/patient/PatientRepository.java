package com.polsl.clinicservicesystem.patient;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
  Set<Patient> findAll();
}

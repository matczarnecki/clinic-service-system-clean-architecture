package com.czarnecki.clinicservicesystem.patient;

import org.springframework.data.repository.CrudRepository;

interface SqlPatientRepository extends PatientRepository, CrudRepository<Patient, Integer> {
}

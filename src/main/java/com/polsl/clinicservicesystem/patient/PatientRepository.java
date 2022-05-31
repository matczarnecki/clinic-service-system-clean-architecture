package com.polsl.clinicservicesystem.patient;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<PatientEntity, Integer> {
  List<PatientEntity> findAll();
}

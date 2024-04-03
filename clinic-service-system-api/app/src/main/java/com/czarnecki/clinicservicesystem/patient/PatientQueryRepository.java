package com.czarnecki.clinicservicesystem.patient;

import java.util.Optional;
import java.util.Set;

public interface PatientQueryRepository {

    Optional<PatientDto> findById(Integer id);

    Set<PatientDto> findAll();

}

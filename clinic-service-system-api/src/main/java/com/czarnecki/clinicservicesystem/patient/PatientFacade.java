package com.czarnecki.clinicservicesystem.patient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PatientFacade {
  private final PatientRepository patientRepository;

  PatientFacade(final PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public Optional<PatientDto> findById(int patientId) {
    return patientRepository.findById(patientId).map(Patient::toDto);
  }

  public List<PatientDto> getPatients() {
    return patientRepository.findAll()
        .stream()
        .map(Patient::toDto)
        .collect(Collectors.toList());
  }

  public void addPatient(PatientDto request) {
    var patient = new Patient();
    patient.setFirstName(request.getFirstName());
    patient.setLastName(request.getLastName());
    patientRepository.save(patient);
  }
}

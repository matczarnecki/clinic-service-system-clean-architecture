package com.czarnecki.clinicservicesystem.patient;

import com.czarnecki.clinicservicesystem.patient.dto.PatientRequestResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PatientFacade {
  private final PatientRepository patientRepository;

  PatientFacade(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public Optional<Patient> findById(int patientId) {
    return patientRepository.findById(patientId);
  }

  public List<PatientRequestResponse> getPatients() {
    return patientRepository.findAll()
        .stream()
        .map(PatientRequestResponse::fromEntity)
        .collect(Collectors.toList());
  }

  public void addPatient(PatientRequestResponse request) {
    Patient patient = new Patient();
    patient.setFirstName(request.getFirstName());
    patient.setLastName(request.getLastName());
    patientRepository.save(patient);
  }
}

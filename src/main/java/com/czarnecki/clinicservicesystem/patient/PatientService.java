package com.czarnecki.clinicservicesystem.patient;

import com.czarnecki.clinicservicesystem.patient.dto.PatientRequestResponse;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class PatientService {
  private final PatientRepository patientRepository;

  PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
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

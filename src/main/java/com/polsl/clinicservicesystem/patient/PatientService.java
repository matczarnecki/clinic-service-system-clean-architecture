package com.polsl.clinicservicesystem.patient;

import com.polsl.clinicservicesystem.patient.dto.PatientRequestResponse;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
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
    PatientEntity patient = new PatientEntity();
    patient.setFirstName(request.getFirstName());
    patient.setLastName(request.getLastName());
    patientRepository.save(patient);
  }
}

package com.polsl.clinicservicesystem.appointment;

import com.polsl.clinicservicesystem.appointment.dto.AppointmentFullResponse;
import com.polsl.clinicservicesystem.appointment.dto.AppointmentRequest;
import com.polsl.clinicservicesystem.appointment.dto.AppointmentResponse;
import com.polsl.clinicservicesystem.appointment.dto.MakeAppointmentRequest;
import com.polsl.clinicservicesystem.exception.BadRequestException;
import com.polsl.clinicservicesystem.patient.Patient;
import com.polsl.clinicservicesystem.user.User;
import com.polsl.clinicservicesystem.patient.PatientRepository;
import com.polsl.clinicservicesystem.user.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AppointmentService {
  private final AppointmentRepository appointmentRepository;
  private final UserRepository userRepository;
  private final PatientRepository patientRepository;

  AppointmentService(AppointmentRepository appointmentRepository,
                            UserRepository userRepository,
                            PatientRepository patientRepository) {
    this.appointmentRepository = appointmentRepository;
    this.userRepository = userRepository;
    this.patientRepository = patientRepository;
  }

  List<AppointmentResponse> getAppointmentsForDay(LocalDate date, Integer doctorId) {
    List<AppointmentResponse> appointments;
    if (doctorId == null) {
      appointments = appointmentRepository.findByAppointmentDate(date)
          .stream()
          .map(AppointmentResponse::fromEntity)
          .collect(Collectors.toList());
    } else {
      appointments = appointmentRepository.findByAppointmentDateAndDoctor_Id(date, doctorId)
          .stream()
          .map(AppointmentResponse::fromEntity)
          .collect(Collectors.toList());
    }
    return appointments;
  }

  List<AppointmentFullResponse> getDoctorAppointmentsForDay(LocalDate date, Integer doctorId) {
    List<AppointmentFullResponse> appointments;
    appointments = appointmentRepository.findByAppointmentDateAndDoctor_Id(date, doctorId)
        .stream()
        .map(AppointmentFullResponse::fromEntity)
        .collect(Collectors.toList());
    return appointments;
  }


  void cancelAppointment(Integer id) {
    Appointment appointment = appointmentRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));
    appointment.setStatus(AppointmentStatus.CANCELLED);
    appointmentRepository.save(appointment);
  }

  void createAppointment(AppointmentRequest request) {
    User doctor = userRepository.findById(request.getDoctorId())
        .orElseThrow(() -> new BadRequestException("Doctor with id: " + request.getDoctorId() + " was not found"));

    Patient patient = patientRepository.findById(request.getPatientId())
        .orElseThrow(() -> new BadRequestException("Patient with id: " + request.getPatientId() + " was not found"));

    Appointment appointment = new Appointment();
    appointment.setAppointmentTime(request.getAppointmentTime());
    appointment.setDoctor(doctor);
    appointment.setPatient(patient);
    appointment.setStatus(AppointmentStatus.SCHEDULED);
    appointmentRepository.save(appointment);
  }

  AppointmentResponse getAppointment(Integer id) {
    Appointment appointment = appointmentRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));
    return AppointmentResponse.fromEntity(appointment);
  }

  List<AppointmentFullResponse> getPatientAppointments(Integer id) {
    Appointment entity = appointmentRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));
    List<AppointmentFullResponse> appointments;
    appointments = appointmentRepository.findAllByAppointmentTimeBefore(entity.getAppointmentTime())
        .stream()
        .map(AppointmentFullResponse::fromEntity)
        .collect(Collectors.toList());
    return appointments;
  }

  void makeAppointment(Integer id, MakeAppointmentRequest request) {
    Appointment entity = appointmentRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));

    entity.setDescription(request.getDescription());
    entity.setDiagnosis(request.getDiagnosis());
    entity.setStatus(AppointmentStatus.DONE);
    appointmentRepository.save(entity);
  }
}

package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentFullResponse;
import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentRequest;
import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentResponse;
import com.czarnecki.clinicservicesystem.appointment.dto.MakeAppointmentRequest;
import com.czarnecki.clinicservicesystem.patient.Patient;
import com.czarnecki.clinicservicesystem.patient.PatientFacade;
import com.czarnecki.clinicservicesystem.user.User;
import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.user.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AppointmentFacade {
  private final AppointmentRepository appointmentRepository;
  private final UserRepository userRepository;
  private final PatientFacade patientFacade;

  AppointmentFacade(final AppointmentRepository appointmentRepository,
                    final UserRepository userRepository,
                    final PatientFacade patientFacade) {
    this.appointmentRepository = appointmentRepository;
    this.userRepository = userRepository;
    this.patientFacade = patientFacade;
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

    Patient patient = patientFacade.findById(request.getPatientId())
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

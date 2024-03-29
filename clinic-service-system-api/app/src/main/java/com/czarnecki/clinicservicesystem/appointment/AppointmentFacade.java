package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentDto;
import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentRequestDto;
import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentShortResponseDto;
import com.czarnecki.clinicservicesystem.appointment.dto.MakeAppointmentRequest;
import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.patient.PatientDto;
import com.czarnecki.clinicservicesystem.patient.PatientFacade;
import com.czarnecki.clinicservicesystem.patient.query.SimplePatientQueryDto;
import com.czarnecki.clinicservicesystem.user.UserFacade;
import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import com.czarnecki.clinicservicesystem.user.query.SimpleUserQueryDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentFacade {
    private final AppointmentFactory appointmentFactory;
    private final AppointmentRepository appointmentRepository;
    private final UserFacade userFacade;
    private final PatientFacade patientFacade;

    AppointmentFacade(final AppointmentFactory appointmentFactory,
                      final AppointmentRepository appointmentRepository,
                      final UserFacade userFacade,
                      final PatientFacade patientFacade) {
        this.appointmentFactory = appointmentFactory;
        this.appointmentRepository = appointmentRepository;
        this.userFacade = userFacade;
        this.patientFacade = patientFacade;
    }

    List<AppointmentDto> getAppointmentsForDay(LocalDate date) {
        return appointmentRepository.findByAppointmentTimeBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay())
                .stream()
                .map((appointment) -> AppointmentDto.builder()
                        .withId(appointment.getId())
                        .withAppointmentTime(appointment.getAppointmentTime())
                        .withDoctor(UserDto.builder()
                                .withId(appointment.getDoctor().getId())
                                .withEmail(appointment.getDoctor().getEmailAddress())
                                .withFirstName(appointment.getDoctor().getFirstName())
                                .withLastName(appointment.getDoctor().getLastName())
                                .build())
                        .withPatient(PatientDto.builder()
                                .withId(appointment.getPatient().getId())
                                .withFirstName(appointment.getPatient().getFirstName())
                                .withLastName(appointment.getPatient().getLastName())
                                .build())
                        .withStatus(appointment.getStatus())
                        .withDiagnosis(appointment.getDiagnosis())
                        .withDescription(appointment.getDescription())
                        .build())
                .toList();
    }

    List<AppointmentDto> getDoctorAppointmentsForDay(LocalDate date, Integer doctorId) {
        return appointmentRepository.findByAppointmentTimeBetweenAndDoctor_Id(date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay(), doctorId)
                .stream()
                .map(appointment -> AppointmentDto.builder()
                        .withId(appointment.getId())
                        .withAppointmentTime(appointment.getAppointmentTime())
                        .withDoctor(UserDto.builder()
                                .withId(appointment.getDoctor().getId())
                                .build())
                        .withPatient(PatientDto.builder()
                                .withId(appointment.getPatient().getId())
                                .withFirstName(appointment.getPatient().getFirstName())
                                .withLastName(appointment.getPatient().getLastName())
                                .build())
                        .withStatus(appointment.getStatus())
                        .withDiagnosis(appointment.getDiagnosis())
                        .withDescription(appointment.getDescription())
                        .build())
                .toList();
    }

    void cancelAppointment(Integer id) {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    void createAppointment(AppointmentRequestDto request) {
        var doctorDto = userFacade.findById(request.getDoctorId())
                .orElseThrow(() -> new BadRequestException("Doctor with id: " + request.getDoctorId() + " was not found"));

        var patientDto = patientFacade.findById(request.getPatientId())
                .orElseThrow(() -> new BadRequestException("Patient with id: " + request.getPatientId() + " was not found"));

        var doctorQueryDto =
                new SimpleUserQueryDto(
                        doctorDto.getId(),
                        doctorDto.getFirstName(),
                        doctorDto.getLastName(),
                        doctorDto.getEmail()
                );

        var patientQueryDto =
                new SimplePatientQueryDto(
                        patientDto.getId(),
                        patientDto.getFirstName(),
                        patientDto.getLastName()
                );

        var appointment = appointmentFactory
                .from(request, doctorQueryDto, patientQueryDto, AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
    }

    AppointmentShortResponseDto getAppointment(Integer id) {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));
        var patientDto = appointment.getPatient();
        var doctorDto = appointment.getDoctor();
        return AppointmentShortResponseDto.builder()
                .withId(appointment.getId())
                .withAppointmentTime(appointment.getAppointmentTime())
                .withDoctor(UserDto.builder()
                        .withId(doctorDto.getId())
                        .build())
                .withPatient(PatientDto.builder()
                        .withId(patientDto.getId())
                        .withFirstName(patientDto.getFirstName())
                        .withLastName(patientDto.getLastName())
                        .build())
                .withStatus(appointment.getStatus())
                .build();
    }

    // TODO this method doesn't do what it's name suggests
    List<AppointmentDto> getPatientAppointments(Integer appointmentId) {
        var entity = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new BadRequestException("Appointment with id: " + appointmentId + " was not found"));
        return appointmentRepository.findAllByAppointmentTimeBefore(entity.getAppointmentTime())
                .stream()
                .map((appointment) -> AppointmentDto.builder()
                        .withId(appointment.getId())
                        .withAppointmentTime(appointment.getAppointmentTime())
                        .withDoctor(UserDto.builder()
                                .withId(appointment.getDoctor().getId())
                                .build())
                        .withPatient(PatientDto.builder()
                                .withId(appointment.getPatient().getId())
                                .withFirstName(appointment.getPatient().getFirstName())
                                .withLastName(appointment.getPatient().getLastName())
                                .build())
                        .withStatus(appointment.getStatus())
                        .withDiagnosis(appointment.getDiagnosis())
                        .withDescription(appointment.getDescription())
                        .build())
                .toList();
    }

    void makeAppointment(Integer id, MakeAppointmentRequest request) {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));

        appointment.setDescription(request.getDescription());
        appointment.setDiagnosis(request.getDiagnosis());
        appointment.setStatus(AppointmentStatus.DONE);
        appointmentRepository.save(appointment);
    }
}

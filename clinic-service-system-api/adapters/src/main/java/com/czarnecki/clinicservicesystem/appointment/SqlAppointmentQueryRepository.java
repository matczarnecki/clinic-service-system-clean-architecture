package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentDto;
import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.patient.PatientDto;
import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface SqlAppointmentQueryRepository extends CrudRepository<SqlAppointment, Integer> {

    Set<SqlAppointment> findByAppointmentTimeBetween(LocalDateTime from, LocalDateTime to);

    Set<SqlAppointment> findByAppointmentTimeBetweenAndDoctorId(LocalDateTime from, LocalDateTime to,
        Integer doctorId);

    Set<SqlAppointment> findAllByAppointmentTimeBefore(LocalDateTime appointmentTime);
}


@Repository
class AppointmentQueryRepositoryImpl implements AppointmentQueryRepository {

    private final SqlAppointmentQueryRepository repository;

    AppointmentQueryRepositoryImpl(final SqlAppointmentQueryRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<AppointmentDto> findById(Integer id) {
        return repository.findById(id)
            .map(appointment -> AppointmentDto
                .builder()
                .withId(appointment.getId())
                .withAppointmentTime(appointment.getAppointmentTime())
                .withDoctor(UserDto.builder()
                    .withId(appointment.getDoctor().getId())
                    .withEmail(appointment.getDoctor().getEmailAddress())
                    .withFirstName(appointment.getDoctor().getFirstName())
                    .withLastName(appointment.getDoctor().getLastName())
                    .build())
                .withPatient(PatientDto
                    .builder()
                    .withId(appointment.getPatient().getId())
                    .withFirstName(appointment.getPatient().getFirstName())
                    .withLastName(appointment.getPatient().getLastName())
                    .build())
                .withStatus(appointment.getStatus())
                .withDiagnosis(appointment.getDiagnosis())
                .withDescription(appointment.getDescription())
                .build());
    }

    @Override
    public List<AppointmentDto> findAppointmentForDay(LocalDate day) {
        var appointments = repository.findByAppointmentTimeBetween(
            day.atStartOfDay(), day.plusDays(1).atStartOfDay());
        return appointments
            .stream()
            .map(appointment -> AppointmentDto
                .builder()
                .withId(appointment.getId())
                .withAppointmentTime(appointment.getAppointmentTime())
                .withDoctor(UserDto.builder()
                    .withId(appointment.getDoctor().getId())
                    .withEmail(appointment.getDoctor().getEmailAddress())
                    .withFirstName(appointment.getDoctor().getFirstName())
                    .withLastName(appointment.getDoctor().getLastName())
                    .build())
                .withPatient(PatientDto
                    .builder()
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

    @Override
    public List<AppointmentDto> findDoctorAppointmentsForDay(
        LocalDate day, Integer doctorId) {
        var appointments = repository.findByAppointmentTimeBetweenAndDoctorId(
            day.atStartOfDay(), day.plusDays(1).atStartOfDay(), doctorId);
        return appointments
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

    @Override
    public List<AppointmentDto> findAllPreviousPatientAppointments(Integer appointmentId) {
        var appointment = findById(appointmentId)
            .orElseThrow(() -> new BadRequestException("Appointment with id: " + appointmentId + " was not found"));

        var patientAppointments = repository
            .findAllByAppointmentTimeBefore(appointment.getAppointmentTime());
        return patientAppointments
            .stream()
            .map(patientAppointment -> AppointmentDto.builder()
                .withId(patientAppointment.getId())
                .withAppointmentTime(patientAppointment.getAppointmentTime())
                .withDoctor(UserDto.builder()
                    .withId(patientAppointment.getDoctor().getId())
                    .build())
                .withPatient(PatientDto.builder()
                    .withId(patientAppointment.getPatient().getId())
                    .withFirstName(patientAppointment.getPatient().getFirstName())
                    .withLastName(patientAppointment.getPatient().getLastName())
                    .build())
                .withStatus(patientAppointment.getStatus())
                .withDiagnosis(patientAppointment.getDiagnosis())
                .withDescription(patientAppointment.getDescription())
                .build())
            .toList();
    }
}

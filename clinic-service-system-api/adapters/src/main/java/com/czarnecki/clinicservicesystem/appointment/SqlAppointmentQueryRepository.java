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
                    .withId(appointment.getDoctor().getSnapshot().id())
                    .withEmail(appointment.getDoctor().getSnapshot().emailAddress())
                    .withFirstName(appointment.getDoctor().getSnapshot().firstName())
                    .withLastName(appointment.getDoctor().getSnapshot().lastName())
                    .build())
                .withPatient(PatientDto
                    .builder()
                    .withId(appointment.getPatient().getSnapshot().id())
                    .withFirstName(appointment.getPatient().getSnapshot().firstName())
                    .withLastName(appointment.getPatient().getSnapshot().lastName())
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
                    .withId(appointment.getDoctor().getSnapshot().id())
                    .withEmail(appointment.getDoctor().getSnapshot().emailAddress())
                    .withFirstName(appointment.getDoctor().getSnapshot().firstName())
                    .withLastName(appointment.getDoctor().getSnapshot().lastName())
                    .build())
                .withPatient(PatientDto
                    .builder()
                    .withId(appointment.getPatient().getSnapshot().id())
                    .withFirstName(appointment.getPatient().getSnapshot().firstName())
                    .withLastName(appointment.getPatient().getSnapshot().lastName())
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
                    .withId(appointment.getDoctor().getSnapshot().id())
                    .build())
                .withPatient(PatientDto.builder()
                    .withId(appointment.getPatient().getSnapshot().id())
                    .withFirstName(appointment.getPatient().getSnapshot().firstName())
                    .withLastName(appointment.getPatient().getSnapshot().lastName())
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
                    .withId(patientAppointment.getDoctor().getSnapshot().id())
                    .build())
                .withPatient(PatientDto.builder()
                    .withId(patientAppointment.getPatient().getSnapshot().id())
                    .withFirstName(patientAppointment.getPatient().getSnapshot().firstName())
                    .withLastName(patientAppointment.getPatient().getSnapshot().lastName())
                    .build())
                .withStatus(patientAppointment.getStatus())
                .withDiagnosis(patientAppointment.getDiagnosis())
                .withDescription(patientAppointment.getDescription())
                .build())
            .toList();
    }
}

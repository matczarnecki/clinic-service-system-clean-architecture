package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentQueryRepository {
    Optional<AppointmentDto> findById(Integer id);

    List<AppointmentDto> findAppointmentForDay(LocalDate day);

    List<AppointmentDto> findDoctorAppointmentsForDay(LocalDate day, Integer doctorId);

    List<AppointmentDto> findAllPreviousPatientAppointments(Integer appointmentId);
}

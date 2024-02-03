package com.czarnecki.clinicservicesystem.appointment;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

interface AppointmentRepository {
    Optional<Appointment> findById(Integer id);

    Appointment save(Appointment entity);

    Set<Appointment> findByAppointmentTimeBetween(LocalDateTime from, LocalDateTime to);

    Set<Appointment> findByAppointmentTimeBetweenAndDoctor_Id(LocalDateTime from, LocalDateTime to, Integer doctorId);

    Set<Appointment> findAllByAppointmentTimeBefore(LocalDateTime appointmentTime);
}

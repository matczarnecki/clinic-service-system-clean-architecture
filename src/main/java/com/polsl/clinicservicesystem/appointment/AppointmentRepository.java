package com.polsl.clinicservicesystem.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

  default Set<Appointment> findByAppointmentDate(LocalDate localDate) {
    return findByAppointmentTimeBetween(localDate.atStartOfDay(), localDate.plusDays(1).atStartOfDay());
  }

  default Set<Appointment> findByAppointmentDateAndDoctor_Id(LocalDate localDate, Integer doctorId) {
    return findByAppointmentTimeBetweenAndDoctor_Id(localDate.atStartOfDay(), localDate.plusDays(1).atStartOfDay(),
        doctorId);
  }

  Set<Appointment> findByAppointmentTimeBetween(LocalDateTime from, LocalDateTime to);

  Set<Appointment> findByAppointmentTimeBetweenAndDoctor_Id(LocalDateTime from, LocalDateTime to, Integer doctorId);

  Set<Appointment> findAllByAppointmentTimeBefore(LocalDateTime appointmentTime);
}

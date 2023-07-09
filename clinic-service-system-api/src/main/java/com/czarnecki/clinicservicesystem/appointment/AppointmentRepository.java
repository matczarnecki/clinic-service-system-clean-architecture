package com.czarnecki.clinicservicesystem.appointment;

import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

  Set<Appointment> findByAppointmentTimeBetween(LocalDateTime from, LocalDateTime to);

  Set<Appointment> findByAppointmentTimeBetweenAndDoctor_Id(LocalDateTime from, LocalDateTime to, Integer doctorId);

  Set<Appointment> findAllByAppointmentTimeBefore(LocalDateTime appointmentTime);
}

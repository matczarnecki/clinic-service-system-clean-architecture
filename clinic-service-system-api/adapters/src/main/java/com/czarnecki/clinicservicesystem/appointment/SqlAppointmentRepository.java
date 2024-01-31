package com.czarnecki.clinicservicesystem.appointment;

import org.springframework.data.repository.CrudRepository;

interface SqlAppointmentRepository extends AppointmentRepository, CrudRepository<Appointment, Integer> {
}


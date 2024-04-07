package com.czarnecki.clinicservicesystem.appointment;

import java.util.Optional;

interface AppointmentRepository {
    Optional<Appointment> findById(Integer id);

    Appointment save(Appointment entity);
}

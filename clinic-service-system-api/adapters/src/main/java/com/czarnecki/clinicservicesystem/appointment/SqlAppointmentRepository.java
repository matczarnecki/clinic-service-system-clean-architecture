package com.czarnecki.clinicservicesystem.appointment;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface SqlAppointmentRepository extends CrudRepository<SqlAppointment, Integer> {
}


@Repository
class AppointmentRepositoryImpl implements AppointmentRepository {
    private final SqlAppointmentRepository repository;

    AppointmentRepositoryImpl(final SqlAppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Appointment> findById(Integer id) {
        return repository.findById(id).map(SqlAppointment::toAppointment);
    }

    @Override
    public Appointment save(Appointment entity) {
        return repository.save(SqlAppointment.fromAppointment(entity)).toAppointment();
    }
}



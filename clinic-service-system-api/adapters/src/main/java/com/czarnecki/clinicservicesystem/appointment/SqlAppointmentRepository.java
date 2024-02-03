package com.czarnecki.clinicservicesystem.appointment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

interface SqlAppointmentRepository extends CrudRepository<SqlAppointment, Integer> {

    Set<SqlAppointment> findByAppointmentTimeBetween(LocalDateTime from, LocalDateTime to);

    Set<SqlAppointment> findByAppointmentTimeBetweenAndDoctor_Id(LocalDateTime from, LocalDateTime to, Integer doctorId);

    Set<SqlAppointment> findAllByAppointmentTimeBefore(LocalDateTime appointmentTime);
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

    @Override
    public Set<Appointment> findByAppointmentTimeBetween(LocalDateTime from, LocalDateTime to) {
        return repository.findByAppointmentTimeBetween(from, to)
                .stream()
                .map(SqlAppointment::toAppointment)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Appointment> findByAppointmentTimeBetweenAndDoctor_Id(LocalDateTime from, LocalDateTime to, Integer doctorId) {
        return repository.findByAppointmentTimeBetweenAndDoctor_Id(from, to, doctorId)
                .stream()
                .map(SqlAppointment::toAppointment)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Appointment> findAllByAppointmentTimeBefore(LocalDateTime appointmentTime) {
        return repository.findAllByAppointmentTimeBefore(appointmentTime)
                .stream()
                .map(SqlAppointment::toAppointment)
                .collect(Collectors.toSet());
    }
}



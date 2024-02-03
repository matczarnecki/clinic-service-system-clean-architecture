package com.czarnecki.clinicservicesystem.patient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

interface SqlPatientRepository extends CrudRepository<SqlPatient, Integer> {
}

@Repository
class PatientRepositoryImpl implements PatientRepository {

    private final SqlPatientRepository repository;

    public PatientRepositoryImpl(final SqlPatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Patient> findById(Integer id) {
        return repository.findById(id)
                .map(SqlPatient::toPatient);
    }

    @Override
    public Set<Patient> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(SqlPatient::toPatient)
                .collect(Collectors.toSet());
    }

    @Override
    public Patient save(Patient entity) {
        return repository.save(SqlPatient.fromPatient(entity)).toPatient();
    }
}

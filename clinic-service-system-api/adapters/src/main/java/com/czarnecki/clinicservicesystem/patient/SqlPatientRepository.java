package com.czarnecki.clinicservicesystem.patient;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
            .map(SqlPatient::toSnapshot)
            .map(Patient::from);
    }

    @Override
    public Patient save(Patient entity) {
        return Patient.from(
            repository.save(SqlPatient.fromSnapshot(entity.getSnapshot()))
                .toSnapshot());
    }
}

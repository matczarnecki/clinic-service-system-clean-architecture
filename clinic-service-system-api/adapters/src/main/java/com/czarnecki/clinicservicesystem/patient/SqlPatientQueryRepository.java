package com.czarnecki.clinicservicesystem.patient;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface SqlPatientQueryRepository extends CrudRepository<SqlPatient, Integer> {
    <T> Optional<T> findById(Integer id, Class<T> type);

    Set<PatientDto> findAllBy();
}


@Repository
class PatientQueryRepositoryImpl implements PatientQueryRepository {

    private final SqlPatientQueryRepository repository;

    PatientQueryRepositoryImpl(final SqlPatientQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PatientDto> findById(Integer id) {
        return repository.findById(id, PatientDto.class);
    }

    @Override
    public Set<PatientDto> findAll() {
        return repository.findAllBy();
    }
}

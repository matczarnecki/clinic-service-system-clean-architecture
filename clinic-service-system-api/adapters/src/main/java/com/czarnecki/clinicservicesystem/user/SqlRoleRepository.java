package com.czarnecki.clinicservicesystem.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

interface SqlRoleRepository extends CrudRepository<SqlRole, String> {
}

@Repository
class RoleRepositoryImpl implements RoleRepository {

    private final SqlRoleRepository repository;

    public RoleRepositoryImpl(SqlRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Role> findById(String id) {
        return repository.findById(id).map(SqlRole::toRole);
    }

    @Override
    public Set<Role> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(SqlRole::toRole)
                .collect(Collectors.toSet());
    }
}



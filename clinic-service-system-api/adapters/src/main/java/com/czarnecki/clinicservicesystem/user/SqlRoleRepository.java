package com.czarnecki.clinicservicesystem.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface SqlRoleRepository extends CrudRepository<SqlRole, String> {
    Optional<SqlRole> findByCode(String code);
}


@Repository
class RoleRepositoryImpl implements RoleRepository {

    private final SqlRoleRepository repository;

    public RoleRepositoryImpl(SqlRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Role> findByCode(String code) {
        return repository.findByCode(code)
            .map(SqlRole::toRole);
    }
}



package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.RoleDto;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface SqlRoleQueryRepository extends CrudRepository<SqlRole, Integer> {
    Set<RoleDto> findAllBy();
}


@Repository
class RoleQueryRepositoryImpl implements RoleQueryRepository {
    private final SqlRoleQueryRepository repository;

    RoleQueryRepositoryImpl(final SqlRoleQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<RoleDto> findAll() {
        return repository.findAllBy();
    }
}

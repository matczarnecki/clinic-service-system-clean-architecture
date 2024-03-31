package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

interface SqlUserQueryRepository extends CrudRepository<SqlUser, Integer> {
    Set<UserDto> findAllByRoleCode(String roleCode);

    Set<UserDto> findAllBy();
}

@Repository
class SqlUserRepositoryImpl implements UserQueryRepository {
    private final SqlUserQueryRepository repository;

    SqlUserRepositoryImpl(final SqlUserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<UserDto> findAll() {
        return repository.findAllBy();
    }

    @Override
    public Set<UserDto> findAllByRoleCode(String roleCode) {
        return repository.findAllByRoleCode(roleCode);
    }
}

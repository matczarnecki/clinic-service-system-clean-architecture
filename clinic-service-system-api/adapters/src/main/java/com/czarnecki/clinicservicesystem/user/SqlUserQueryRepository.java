package com.czarnecki.clinicservicesystem.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

interface SqlUserQueryRepository extends CrudRepository<SqlUser, Integer> {
    Set<SqlUser> findAllByRoleCode(String roleCode);
}

@Repository
class SqlUserRepositoryImpl implements UserQueryRepository {
    private final SqlUserQueryRepository repository;

    SqlUserRepositoryImpl(final SqlUserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<User> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(SqlUser::toUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<User> findAllByRoleCode(String roleCode) {
        return repository.findAllByRoleCode(roleCode)
                .stream()
                .map(SqlUser::toUser)
                .collect(Collectors.toSet());
    }
}

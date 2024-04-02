package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface SqlUserQueryRepository extends CrudRepository<SqlUser, Integer> {
    <T> Optional<T> findById(Integer id, Class<T> type);

    Set<UserDto> findAllByRoleCode(String roleCode);

    Set<UserDto> findAllBy();
}


@Repository
class UserQueryRepositoryImpl implements UserQueryRepository {
    private final SqlUserQueryRepository repository;

    UserQueryRepositoryImpl(final SqlUserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserDto> findById(Integer id) {
        return repository.findById(id, UserDto.class);
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

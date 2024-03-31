package com.czarnecki.clinicservicesystem.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

interface SqlUserRepository extends CrudRepository<SqlUser, Integer> {
    Optional<SqlUser> findByUsername(String username);

    Set<SqlUser> findAllByRole_Code(String doc);

    boolean existsByUsername(String username);

    boolean existsByEmailAddress(String emailAddress);
}

@Repository
class UserRepositoryImpl implements UserRepository {
    private final SqlUserRepository repository;

    UserRepositoryImpl(SqlUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return repository.findById(id)
                .map(SqlUser::toUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(SqlUser::toUser);
    }

    @Override
    public Set<User> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(SqlUser::toUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<User> findAllByRoleCode(String code) {
        return repository.findAllByRole_Code(code)
                .stream()
                .map(SqlUser::toUser)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmailAddress(String emailAddress) {
        return repository.existsByEmailAddress(emailAddress);
    }

    @Override
    public User save(User entity) {
        return repository.save(SqlUser.fromUser(entity)).toUser();
    }
}

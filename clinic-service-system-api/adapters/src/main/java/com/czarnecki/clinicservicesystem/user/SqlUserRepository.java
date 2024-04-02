package com.czarnecki.clinicservicesystem.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface SqlUserRepository extends CrudRepository<SqlUser, Integer> {
    Optional<SqlUser> findByUsername(String username);

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

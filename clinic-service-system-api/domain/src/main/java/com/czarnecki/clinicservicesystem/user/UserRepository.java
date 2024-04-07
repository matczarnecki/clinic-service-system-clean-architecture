package com.czarnecki.clinicservicesystem.user;

import java.util.Optional;

interface UserRepository {

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmailAddress(String emailAddress);

    User save(User entity);
}

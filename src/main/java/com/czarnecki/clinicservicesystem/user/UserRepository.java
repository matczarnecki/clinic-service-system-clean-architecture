package com.czarnecki.clinicservicesystem.user;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  Optional<User> findByUsername(String username);

  Set<User> findAll();

  Set<User> findAllByRole_Code(String doc);

  boolean existsByUsername(String username);

  boolean existsByEmailAddress(String emailAddress);
}

package com.czarnecki.clinicservicesystem.user;

import java.util.Optional;
import java.util.Set;

interface UserRepository  {

  Optional<User> findById(Integer id);

  Optional<User> findByUsername(String username);

  Set<User> findAll();

  Set<User> findAllByRole_Code(String doc);

  boolean existsByUsername(String username);

  boolean existsByEmailAddress(String emailAddress);

  User save(User entity);
}

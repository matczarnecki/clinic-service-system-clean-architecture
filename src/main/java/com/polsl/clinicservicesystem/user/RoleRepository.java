package com.polsl.clinicservicesystem.user;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
  Set<Role> findAll();
}

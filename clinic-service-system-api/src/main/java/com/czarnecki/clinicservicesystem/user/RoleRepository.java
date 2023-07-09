package com.czarnecki.clinicservicesystem.user;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;

interface RoleRepository extends CrudRepository<Role, String> {
  Set<Role> findAll();
}

package com.polsl.clinicservicesystem.user;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, String> {
  List<RoleEntity> findAll();
}

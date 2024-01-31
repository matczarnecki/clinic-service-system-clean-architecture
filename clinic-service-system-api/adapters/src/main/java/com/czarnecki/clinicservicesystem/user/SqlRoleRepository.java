package com.czarnecki.clinicservicesystem.user;

import org.springframework.data.repository.CrudRepository;

public interface SqlRoleRepository extends RoleRepository, CrudRepository<Role, String> {
}

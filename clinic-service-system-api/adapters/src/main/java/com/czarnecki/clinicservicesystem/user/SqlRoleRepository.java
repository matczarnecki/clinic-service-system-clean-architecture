package com.czarnecki.clinicservicesystem.user;

import org.springframework.data.repository.CrudRepository;

interface SqlRoleRepository extends RoleRepository, CrudRepository<Role, String> {
}

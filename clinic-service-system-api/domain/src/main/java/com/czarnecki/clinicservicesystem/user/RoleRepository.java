package com.czarnecki.clinicservicesystem.user;

import java.util.Optional;
import java.util.Set;

interface RoleRepository {

    Optional<Role> findById(String id);

    Set<Role> findAll();
}

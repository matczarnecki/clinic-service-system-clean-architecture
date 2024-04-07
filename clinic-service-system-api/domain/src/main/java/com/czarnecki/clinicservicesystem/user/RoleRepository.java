package com.czarnecki.clinicservicesystem.user;

import java.util.Optional;

interface RoleRepository {

    Optional<Role> findByCode(String code);

}

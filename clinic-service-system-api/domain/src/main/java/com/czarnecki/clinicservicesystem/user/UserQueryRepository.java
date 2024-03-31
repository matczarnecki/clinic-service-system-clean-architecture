package com.czarnecki.clinicservicesystem.user;

import java.util.Set;

public interface UserQueryRepository {

    Set<User> findAll();

    Set<User> findAllByRoleCode(String code);
}

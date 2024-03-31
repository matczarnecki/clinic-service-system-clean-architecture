package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.UserDto;

import java.util.Set;

public interface UserQueryRepository {

    Set<UserDto> findAll();

    Set<UserDto> findAllByRoleCode(String code);
}

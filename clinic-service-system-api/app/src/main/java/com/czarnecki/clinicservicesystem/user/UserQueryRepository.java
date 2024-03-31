package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.UserDto;

import java.util.Optional;
import java.util.Set;

public interface UserQueryRepository {

    Optional<UserDto> findById(Integer id);

    Set<UserDto> findAll();

    Set<UserDto> findAllByRoleCode(String code);
}

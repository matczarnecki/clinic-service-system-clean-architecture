package com.czarnecki.clinicservicesystem.user.vo;

import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;

public record UserSnapshot(Integer id,
                           String username,
                           String password,
                           String firstName,
                           String lastName,
                           String emailAddress,
                           boolean isActive,
                           boolean isBlocked,
                           Integer numberOfFailedLogins,
                           RoleSnapshot role)
    implements EntitySnapshot<Integer> {
}

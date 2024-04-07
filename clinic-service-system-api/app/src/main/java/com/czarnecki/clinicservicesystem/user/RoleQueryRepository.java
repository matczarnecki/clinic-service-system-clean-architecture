package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.RoleDto;
import java.util.Set;

public interface RoleQueryRepository {

    Set<RoleDto> findAll();
}

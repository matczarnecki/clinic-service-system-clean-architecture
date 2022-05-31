package com.polsl.clinicservicesystem.user;

import com.polsl.clinicservicesystem.user.dto.RoleResponse;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

  private final RoleRepository roleRepository;

  RolesService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<?> getRoles() {
    return roleRepository
        .findAll()
        .stream()
        .map(RoleResponse::fromEntity)
        .collect(Collectors.toList());
  }
}

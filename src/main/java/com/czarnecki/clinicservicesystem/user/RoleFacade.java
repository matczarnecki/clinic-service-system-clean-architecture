package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.RoleResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RoleFacade {

  private final RoleRepository roleRepository;

  RoleFacade(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<?> getRoles() {
    return roleRepository
        .findAll()
        .stream()
        .map(RoleResponse::fromEntity)
        .collect(Collectors.toList());
  }

  public Optional<Role> findById(String roleId) {
    return roleRepository.findById(roleId);
  }
}

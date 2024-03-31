package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.query.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleFacade {
    private final RoleRepository roleRepository;

    RoleFacade(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(this::roleToRoleDto)
                .toList();
    }

    public Optional<Role> findById(String roleId) {
        return roleRepository.findById(roleId);
    }

    private RoleDto roleToRoleDto(Role role) {
        return RoleDto.builder()
                .withCode(role.getCode())
                .withName(role.getName())
                .build();
    }
}

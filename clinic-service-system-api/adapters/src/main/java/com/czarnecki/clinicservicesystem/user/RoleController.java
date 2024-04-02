package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.RoleDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/roles")
class RoleController {
    private final RoleQueryRepository roleQueryRepository;

    RoleController(final RoleQueryRepository roleQueryRepository) {
        this.roleQueryRepository = roleQueryRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_SEE_ROLES')")
    ResponseEntity<List<RoleDto>> getRoles() {
        return ResponseEntity.ok(roleQueryRepository
            .findAll()
            .stream()
            .toList());
    }
}

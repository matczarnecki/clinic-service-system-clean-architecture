package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.query.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/roles")
class RoleController {

    private final RoleFacade roleFacade;

    RoleController(final RoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_SEE_ROLES')")
    ResponseEntity<List<RoleDto>> getRoles() {
        return ResponseEntity.ok(roleFacade.getRoles());
    }
}

package com.czarnecki.clinicservicesystem.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/roles")
class RoleController {

  private final RoleFacade rolesService;

  RoleController(RoleFacade rolesService) {
    this.rolesService = rolesService;
  }

  @GetMapping()
  @PreAuthorize("hasAuthority('CAN_SEE_ROLES')")
  ResponseEntity<?> getRoles() {
    return ResponseEntity.ok(rolesService.getRoles());
  }
}

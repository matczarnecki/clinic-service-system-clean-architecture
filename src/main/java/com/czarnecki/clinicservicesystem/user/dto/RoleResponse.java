package com.czarnecki.clinicservicesystem.user.dto;

import com.czarnecki.clinicservicesystem.user.Role;

public class RoleResponse {
  private String code;
  private String name;

  public String getCode() {
    return code;
  }

  void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  public static RoleResponse fromEntity(Role entity) {
    RoleResponse role = new RoleResponse();
    role.setCode(entity.getCode());
    role.setName(entity.getName());
    return role;
  }
}

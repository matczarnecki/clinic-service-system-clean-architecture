package com.czarnecki.clinicservicesystem.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {

  @Id
  private String code;

  private String name;

  private String description;

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

  public String getDescription() {
    return description;
  }

  void setDescription(String description) {
    this.description = description;
  }
}

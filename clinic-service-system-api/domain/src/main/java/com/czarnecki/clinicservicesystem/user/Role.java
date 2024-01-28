package com.czarnecki.clinicservicesystem.user;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

  @Id
  private String code;
  private String name;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "role_authorities",
      joinColumns = @JoinColumn(name = "role_code", referencedColumnName = "code", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "authority_code", referencedColumnName = "code", nullable = false))
  private Set<Authority> authorities;

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

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }
}

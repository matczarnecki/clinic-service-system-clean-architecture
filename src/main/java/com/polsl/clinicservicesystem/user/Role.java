package com.polsl.clinicservicesystem.user;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

  @Id
  private String code;

  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "role_authorities",
      joinColumns = @JoinColumn(name = "role_code", referencedColumnName = "code", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "authority_code", referencedColumnName = "code", nullable = false))
  private Set<Authority> authorities;


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }
}

package com.czarnecki.clinicservicesystem.auth;

class AuthenticationResponse {

  private String jwt;

  public AuthenticationResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  void setJwt(String jwt) {
    this.jwt = jwt;
  }
}

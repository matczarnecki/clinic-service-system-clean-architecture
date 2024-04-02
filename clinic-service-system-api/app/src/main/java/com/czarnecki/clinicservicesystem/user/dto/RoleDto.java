package com.czarnecki.clinicservicesystem.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RoleDto.Builder.class)
public record RoleDto(String code, String name) {

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder
  public static class Builder {
    private String code;
    private String name;

    private Builder() {

    }

    public RoleDto build() {
      return new RoleDto(this.code, this.name);
    }

    public Builder withCode(final String code) {
      this.code = code;
      return this;
    }

    public Builder withName(final String name) {
      this.name = name;
      return this;
    }
  }
}

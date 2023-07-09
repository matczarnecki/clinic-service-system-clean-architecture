package com.czarnecki.clinicservicesystem.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RoleDto.Builder.class)
public class RoleDto {
  private final String code;
  private final String name;

  private RoleDto(Builder builder) {
    this.code = builder.code;
    this.name = builder.name;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  @JsonPOJOBuilder
  public static class Builder {
    private String code;
    private String name;

    private Builder() {

    }

    public RoleDto build() {
      return new RoleDto(this);
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

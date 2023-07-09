package com.czarnecki.clinicservicesystem.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UserDto.Builder.class)
public class UserDto {
  private final Integer id;
  private final String username;
  private final String email;
  private final boolean isActive;
  private final String role;
  private final String firstName;
  private final String lastName;
  private final boolean isBlocked;

  private UserDto(Builder builder) {
    this.id = builder.id;
    this.username = builder.username;
    this.email = builder.email;
    this.isActive = builder.isActive;
    this.role = builder.role;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.isBlocked = builder.isBlocked;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public boolean isActive() {
    return isActive;
  }

  public String getRole() {
    return role;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  @JsonPOJOBuilder
  public static class Builder {
    private Integer id;
    private String username;
    private String email;
    private boolean isActive;
    private String role;
    private String firstName;
    private String lastName;
    private boolean isBlocked;

    private Builder() {

    }

    public UserDto build() {
      return new UserDto(this);
    }

    public Builder withId(final Integer id) {
      this.id = id;
      return this;
    }

    public Builder withUsername(final String username) {
      this.username = username;
      return this;
    }

    public Builder withEmail(final String email) {
      this.email = email;
      return this;
    }

    public Builder withActive(final boolean active) {
      isActive = active;
      return this;
    }

    public Builder withRole(final String role) {
      this.role = role;
      return this;
    }

    public Builder withFirstName(final String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder withLastName(final String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder withBlocked(final boolean blocked) {
      isBlocked = blocked;
      return this;
    }
  }
}

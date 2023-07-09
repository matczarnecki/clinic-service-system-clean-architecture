package com.czarnecki.clinicservicesystem.appointment.dto;

import com.czarnecki.clinicservicesystem.appointment.AppointmentStatus;
import com.czarnecki.clinicservicesystem.patient.PatientDto;
import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.LocalDateTime;

@JsonDeserialize(builder = AppointmentShortResponseDto.Builder.class)
public class AppointmentShortResponseDto {

  private final Integer id;
  private final LocalDateTime appointmentTime;
  private final UserDto doctor;
  private final PatientDto patient;
  private final AppointmentStatus status;

  private AppointmentShortResponseDto(Builder builder) {
    this.id = builder.id;
    this.appointmentTime = builder.appointmentTime;
    this.doctor = builder.doctor;
    this.patient = builder.patient;
    this.status = builder.status;
  }

  public static Builder builder() {
    return new Builder();
  }

  Integer getId() {
    return id;
  }

  LocalDateTime getAppointmentTime() {
    return appointmentTime;
  }

  UserDto getDoctor() {
    return doctor;
  }

  PatientDto getPatient() {
    return patient;
  }

  AppointmentStatus getStatus() {
    return status;
  }

  @JsonPOJOBuilder
  public static class Builder {
    private Integer id;
    private LocalDateTime appointmentTime;
    private UserDto doctor;
    private PatientDto patient;
    private AppointmentStatus status;

    private Builder() {

    }

    public AppointmentShortResponseDto build() {
      return new AppointmentShortResponseDto(this);
    }

    public Builder withId(final Integer id) {
      this.id = id;
      return this;
    }

    public Builder withAppointmentTime(final LocalDateTime appointmentTime) {
      this.appointmentTime = appointmentTime;
      return this;
    }

    public Builder withDoctor(final UserDto doctor) {
      this.doctor = doctor;
      return this;
    }

    public Builder withPatient(final PatientDto patient) {
      this.patient = patient;
      return this;
    }

    public Builder withStatus(final AppointmentStatus status) {
      this.status = status;
      return this;
    }
  }
}

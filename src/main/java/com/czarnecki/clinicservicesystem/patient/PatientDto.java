package com.czarnecki.clinicservicesystem.patient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotBlank;

@JsonDeserialize(builder = PatientDto.Builder.class)
public class PatientDto {
    public static Builder builder() {
        return new Builder();
    }

    private final Integer id;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;

    private PatientDto(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonPOJOBuilder
    public static class Builder {
        private Integer id;
        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;

        private Builder() {

        }

        public PatientDto build() {
            return new PatientDto(this);
        }

        public Builder withId(final Integer id) {
            this.id = id;
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
    }
}

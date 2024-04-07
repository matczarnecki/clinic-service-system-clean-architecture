package com.czarnecki.clinicservicesystem.patient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotBlank;

@JsonDeserialize(builder = PatientDto.Builder.class)
public record PatientDto(Integer id, @NotBlank String firstName, @NotBlank String lastName) {

    public static Builder builder() {
        return new Builder();
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
            return new PatientDto(id, firstName, lastName);
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

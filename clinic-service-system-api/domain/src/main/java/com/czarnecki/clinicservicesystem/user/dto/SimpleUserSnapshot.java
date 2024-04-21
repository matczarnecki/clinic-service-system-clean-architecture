package com.czarnecki.clinicservicesystem.user.dto;

public record SimpleUserSnapshot(Integer id,
                                 String firstName,
                                 String lastName,
                                 String emailAddress) {
}

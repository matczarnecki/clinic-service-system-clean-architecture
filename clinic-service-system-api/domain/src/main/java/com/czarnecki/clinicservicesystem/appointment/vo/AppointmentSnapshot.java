package com.czarnecki.clinicservicesystem.appointment.vo;

import com.czarnecki.clinicservicesystem.appointment.AppointmentStatus;
import com.czarnecki.clinicservicesystem.patient.dto.SimplePatientSnapshot;
import com.czarnecki.clinicservicesystem.user.dto.SimpleUserSnapshot;
import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;
import java.time.LocalDateTime;

public record AppointmentSnapshot(Integer id,
                                  LocalDateTime appointmentTime,
                                  SimpleUserSnapshot doctor,
                                  SimplePatientSnapshot patient,
                                  AppointmentStatus status,
                                  String diagnosis,
                                  String description) implements EntitySnapshot<Integer> {
}

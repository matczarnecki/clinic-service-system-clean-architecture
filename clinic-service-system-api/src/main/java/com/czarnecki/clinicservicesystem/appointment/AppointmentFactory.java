package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentRequestDto;
import com.czarnecki.clinicservicesystem.patient.query.SimplePatientQueryDto;
import com.czarnecki.clinicservicesystem.user.query.SimpleUserQueryDto;
import org.springframework.stereotype.Service;

@Service
class AppointmentFactory {
  Appointment from(final AppointmentRequestDto source,
                   final SimpleUserQueryDto doctor,
                   final SimplePatientQueryDto patient,
                   final AppointmentStatus status) {
    var result = new Appointment();
    result.setAppointmentTime(source.getAppointmentTime());
    result.setDoctor(doctor);
    result.setPatient(patient);
    result.setStatus(status);
    return result;
  }
}

package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentRequestDto;
import com.czarnecki.clinicservicesystem.patient.dto.SimplePatient;
import com.czarnecki.clinicservicesystem.user.dto.SimpleUser;
import org.springframework.stereotype.Service;

@Service
class AppointmentFactory {
    Appointment from(final AppointmentRequestDto source,
                     final SimpleUser doctor,
                     final SimplePatient patient,
                     final AppointmentStatus status) {
        var result = new Appointment();
        result.setAppointmentTime(source.getAppointmentTime());
        result.setDoctor(doctor);
        result.setPatient(patient);
        result.setStatus(status);
        return result;
    }
}

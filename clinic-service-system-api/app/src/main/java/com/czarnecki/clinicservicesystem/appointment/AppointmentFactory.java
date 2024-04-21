package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentRequestDto;
import com.czarnecki.clinicservicesystem.patient.dto.SimplePatient;
import com.czarnecki.clinicservicesystem.user.dto.SimpleUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
class AppointmentFactory {
    Appointment from(final AppointmentRequestDto source,
        final SimpleUser doctor,
        final SimplePatient patient) {
        return new Appointment(null, source.getAppointmentTime(), doctor, patient, AppointmentStatus.SCHEDULED,
            StringUtils.EMPTY, StringUtils.EMPTY);
    }
}

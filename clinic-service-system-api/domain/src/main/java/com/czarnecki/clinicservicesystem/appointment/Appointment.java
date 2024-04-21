package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.Aggregate;
import com.czarnecki.clinicservicesystem.appointment.vo.AppointmentSnapshot;
import com.czarnecki.clinicservicesystem.patient.dto.SimplePatient;
import com.czarnecki.clinicservicesystem.user.dto.SimpleUser;
import java.time.LocalDateTime;

class Appointment
    implements Aggregate<Integer, AppointmentSnapshot> {

    static Appointment from(final AppointmentSnapshot snapshot) {
        return new Appointment(snapshot.id(),
            snapshot.appointmentTime(),
            SimpleUser.from(snapshot.doctor()),
            SimplePatient.from(snapshot.patient()),
            snapshot.status(),
            snapshot.diagnosis(), snapshot.description());
    }

    private Integer id;
    private LocalDateTime appointmentTime;
    private SimpleUser doctor;
    private SimplePatient patient;
    private AppointmentStatus status;
    private String diagnosis;
    private String description;

    public Appointment(Integer id, LocalDateTime appointmentTime, SimpleUser doctor, SimplePatient patient,
        AppointmentStatus status, String diagnosis, String description) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
        this.diagnosis = diagnosis;
        this.description = description;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public AppointmentSnapshot getSnapshot() {
        return new AppointmentSnapshot(id, appointmentTime,
            doctor.getSnapshot(), patient.getSnapshot(),
            status, diagnosis, description);
    }
}

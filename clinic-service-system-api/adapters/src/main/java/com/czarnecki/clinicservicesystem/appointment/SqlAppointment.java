package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.vo.AppointmentSnapshot;
import com.czarnecki.clinicservicesystem.patient.SqlSimplePatient;
import com.czarnecki.clinicservicesystem.user.SqlSimpleUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
class SqlAppointment {
    static SqlAppointment fromSnapshot(com.czarnecki.clinicservicesystem.appointment.vo.AppointmentSnapshot snapshot) {
        return new SqlAppointment(snapshot.id(), snapshot.appointmentTime(),
            SqlSimpleUser.fromSnapshot(snapshot.doctor()),
            SqlSimplePatient.fromSnapshot(snapshot.patient()),
            snapshot.status(), snapshot.diagnosis(), snapshot.description());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime appointmentTime;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private SqlSimpleUser doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private SqlSimplePatient patient;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    private String diagnosis;
    private String description;

    public SqlAppointment(Integer id, LocalDateTime appointmentTime, SqlSimpleUser doctor, SqlSimplePatient patient,
        AppointmentStatus status, String diagnosis, String description) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
        this.diagnosis = diagnosis;
        this.description = description;
    }

    public SqlAppointment() {
    }

    AppointmentSnapshot getSnapshot() {
        return new AppointmentSnapshot(id, appointmentTime, doctor.getSnapshot(), patient.getSnapshot(), status,
            diagnosis, description);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public SqlSimpleUser getDoctor() {
        return doctor;
    }

    public SqlSimplePatient getPatient() {
        return patient;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDescription() {
        return description;
    }
}

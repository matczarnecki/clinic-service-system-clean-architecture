package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.patient.SqlSimplePatient;
import com.czarnecki.clinicservicesystem.patient.dto.SimplePatient;
import com.czarnecki.clinicservicesystem.user.SqlSimpleUser;
import com.czarnecki.clinicservicesystem.user.dto.SimpleUser;
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
    static SqlAppointment fromAppointment(Appointment source) {
        var result = new SqlAppointment();
        result.id = source.getId();
        result.appointmentTime = source.getAppointmentTime();
        result.doctor = source.getDoctor() == null ? null : SqlSimpleUser.fromSimpleUser(source.getDoctor());
        result.patient = source.getPatient() == null ? null : SqlSimplePatient
            .fromSnapshot(source.getPatient().getSnapshot());
        result.status = source.getStatus();
        result.diagnosis = source.getDiagnosis();
        result.description = source.getDescription();
        return result;
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

    Appointment toAppointment() {
        var result = new Appointment();
        result.setId(id);
        result.setAppointmentTime(appointmentTime);
        result.setDoctor(doctor == null ? null : doctor.toSimpleUser());
        result.setPatient(patient == null ? null : SimplePatient.from(patient.toSnapshot()));
        result.setStatus(status);
        result.setDiagnosis(diagnosis);
        result.setDescription(description);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public SimpleUser getDoctor() {
        return doctor.toSimpleUser();
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

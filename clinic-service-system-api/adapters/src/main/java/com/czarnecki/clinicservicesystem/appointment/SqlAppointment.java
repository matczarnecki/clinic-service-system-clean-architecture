package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.query.SimpleUserQueryDto;
import com.czarnecki.clinicservicesystem.patient.query.SimplePatientQueryDto;
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
        result.doctor = source.getDoctor();
        result.patient = source.getPatient();
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
    private SimpleUserQueryDto doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private SimplePatientQueryDto patient;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    private String diagnosis;
    private String description;

    Appointment toAppointment() {
        var result = new Appointment();
        result.setId(id);
        result.setAppointmentTime(appointmentTime);
        result.setDoctor(doctor);
        result.setPatient(patient);
        result.setStatus(status);
        result.setDiagnosis(diagnosis);
        result.setDescription(description);
        return result;
    }
}

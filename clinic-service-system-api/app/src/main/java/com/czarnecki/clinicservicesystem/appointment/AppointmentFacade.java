package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentRequestDto;
import com.czarnecki.clinicservicesystem.appointment.dto.MakeAppointmentRequest;
import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.patient.PatientQueryRepository;
import com.czarnecki.clinicservicesystem.patient.dto.SimplePatient;
import com.czarnecki.clinicservicesystem.patient.dto.SimplePatientSnapshot;
import com.czarnecki.clinicservicesystem.user.UserQueryRepository;
import com.czarnecki.clinicservicesystem.user.dto.SimpleUser;
import org.springframework.stereotype.Service;

@Service
public class AppointmentFacade {
    private final AppointmentFactory appointmentFactory;
    private final AppointmentRepository appointmentRepository;
    private final UserQueryRepository userQueryRepository;
    private final PatientQueryRepository patientQueryRepository;

    AppointmentFacade(final AppointmentFactory appointmentFactory,
        final AppointmentRepository appointmentRepository,
        final UserQueryRepository userQueryRepository,
        final PatientQueryRepository patientQueryRepository) {
        this.appointmentFactory = appointmentFactory;
        this.appointmentRepository = appointmentRepository;
        this.userQueryRepository = userQueryRepository;
        this.patientQueryRepository = patientQueryRepository;
    }

    void cancelAppointment(Integer id) {
        var appointment = appointmentRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    void createAppointment(AppointmentRequestDto request) {
        var doctorDto = userQueryRepository.findById(request.getDoctorId())
            .orElseThrow(() -> new BadRequestException("Doctor with id: " + request.getDoctorId() + " was not found"));

        var patientDto = patientQueryRepository.findById(request.getPatientId())
            .orElseThrow(
                () -> new BadRequestException("Patient with id: " + request.getPatientId() + " was not found"));

        var simpleUserDoctor =
            new SimpleUser(
                doctorDto.getId(),
                doctorDto.getFirstName(),
                doctorDto.getLastName(),
                doctorDto.getEmailAddress()
            );

        var simplePatient = SimplePatient
            .from(new SimplePatientSnapshot(patientDto.id(), patientDto.firstName(), patientDto.lastName()));

        var appointment = appointmentFactory
            .from(request, simpleUserDoctor, simplePatient, AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
    }

    void makeAppointment(Integer id, MakeAppointmentRequest request) {
        var appointment = appointmentRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found"));

        appointment.setDescription(request.getDescription());
        appointment.setDiagnosis(request.getDiagnosis());
        appointment.setStatus(AppointmentStatus.DONE);
        appointmentRepository.save(appointment);
    }
}

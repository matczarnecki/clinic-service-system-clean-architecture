package com.czarnecki.clinicservicesystem.appointment;

import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentDto;
import com.czarnecki.clinicservicesystem.appointment.dto.AppointmentRequestDto;
import com.czarnecki.clinicservicesystem.appointment.dto.MakeAppointmentRequest;
import com.czarnecki.clinicservicesystem.auth.CustomUserDetails;
import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/appointments")
class AppointmentController {
    private final AppointmentFacade appointmentFacade;
    private final AppointmentQueryRepository appointmentQueryRepository;

    AppointmentController(final AppointmentFacade appointmentService,
        final AppointmentQueryRepository appointmentQueryRepository) {
        this.appointmentFacade = appointmentService;
        this.appointmentQueryRepository = appointmentQueryRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_SEE_APPOINTMENTS')")
    ResponseEntity<List<AppointmentDto>> getAppointments(@RequestParam(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentQueryRepository.findAppointmentForDay(date)
            .stream()
            .toList());
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAuthority('CAN_SEE_FULL_APPOINTMENTS')")
    ResponseEntity<List<AppointmentDto>> getDoctorAppointments(
        @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date, Authentication auth) {
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        return ResponseEntity.ok(
            appointmentQueryRepository.findDoctorAppointmentsForDay(date, user.getId())
                .stream()
                .toList());
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_CANCEL_APPOINTMENTS')")
    ResponseEntity<String> cancelAppointment(@PathVariable Integer id) {
        appointmentFacade.cancelAppointment(id);
        return ResponseEntity.ok("The appointment was cancelled!");
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('CAN_ADD_APPOINTMENTS')")
    ResponseEntity<String> addAppointment(@RequestBody @Valid AppointmentRequestDto request) {
        appointmentFacade.createAppointment(request);
        return ResponseEntity.ok("The appointment was scheduled!");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_SEE_FULL_APPOINTMENTS')")
    ResponseEntity<AppointmentDto> getAppointment(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentQueryRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Appointment with id: " + id + " was not found")));
    }

    @GetMapping("/patient/{appointmentId}")
    @PreAuthorize("hasAuthority('CAN_SEE_PATIENT_APPOINTMENTS')")
    ResponseEntity<List<AppointmentDto>> getPatientAppointments(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(appointmentQueryRepository.findAllPreviousPatientAppointments(appointmentId)
            .stream()
            .toList());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_MAKE_APPOINTMENTS')")
    ResponseEntity<String> makeAppointment(@PathVariable Integer id,
        @RequestBody @Valid MakeAppointmentRequest request) {
        appointmentFacade.makeAppointment(id, request);
        return ResponseEntity.ok("The appointment has been finished!");
    }
}

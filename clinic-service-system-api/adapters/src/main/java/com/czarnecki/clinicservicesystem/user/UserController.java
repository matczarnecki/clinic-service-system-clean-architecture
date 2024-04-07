package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.auth.CustomUserDetails;
import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.user.dto.EditUserRequest;
import com.czarnecki.clinicservicesystem.user.dto.RegisterUserRequest;
import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import jakarta.validation.Valid;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/users")
class UserController {
    private final UserFacade userFacade;
    private final UserQueryRepository userQueryRepository;

    UserController(final UserFacade userFacade,
        final UserQueryRepository userQueryRepository) {
        this.userFacade = userFacade;
        this.userQueryRepository = userQueryRepository;
    }

    @PostMapping("/registration")
    ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        userFacade.registerNewUser(request);
        return ResponseEntity.ok("Successful registration");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_SEE_USERS')")
    List<UserDto> getUsers() {
        return userQueryRepository.findAll()
            .stream()
            .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_SEE_USERS')")
    ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        return userQueryRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new BadRequestException("User not found"));
    }

    @GetMapping("/me")
    ResponseEntity<UserDto> getLoggedUser(Authentication auth) {
        var user = (CustomUserDetails) auth.getPrincipal();
        return userQueryRepository.findById(user.getId())
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new BadRequestException("User not found"));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_EDIT_USERS')")
    ResponseEntity<String> editUser(@PathVariable Integer id, @RequestBody @Valid EditUserRequest request) {
        userFacade.editUser(id, request);
        return ResponseEntity.ok("User has been modified");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_EDIT_USERS')")
    ResponseEntity<String> disableUser(@PathVariable Integer id) {
        userFacade.disableUser(id);
        return ResponseEntity.ok("User has been disabled");
    }

    @PatchMapping("{id}/unlock")
    @PreAuthorize("hasAuthority('CAN_EDIT_USERS')")
    ResponseEntity<String> unlockAccount(@PathVariable Integer id) {
        userFacade.unlockAccount(id);
        return ResponseEntity.ok("Account has been unlocked");
    }

    @GetMapping("/doctors")
    @PreAuthorize("hasAuthority('CAN_SEE_DOCTORS')")
    List<UserDto> getDoctors() {
        return userQueryRepository.findAllByRoleCode("DOC")
            .stream()
            .toList();
    }
}

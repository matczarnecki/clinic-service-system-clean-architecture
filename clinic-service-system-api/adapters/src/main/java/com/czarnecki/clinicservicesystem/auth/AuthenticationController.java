package com.czarnecki.clinicservicesystem.auth;


import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.user.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/authentication")
class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService customUserDetailsService;
    private final UserFacade userFacade;
    private final JwtUtility jwtUtility;

    AuthenticationController(AuthenticationManager authenticationManager,
        UserDetailsService customUserDetailsService,
        final UserFacade userService, JwtUtility jwtUtility) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.userFacade = userService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping
    ResponseEntity<AuthenticationResponse> authenticateUser(@Valid @RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            userFacade.handleFailedAuthentication(request.getUsername());
            throw new BadRequestException("Incorrect username or password", e);
        }

        userFacade.handleCorrectAuthentication(request.getUsername());
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

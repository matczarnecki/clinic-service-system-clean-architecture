package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.user.dto.EditUserRequest;
import com.czarnecki.clinicservicesystem.user.dto.RegisterUserRequest;
import com.czarnecki.clinicservicesystem.user.dto.UserDto;
import com.czarnecki.clinicservicesystem.user.vo.UserSnapshot;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final int MAX_NUMBER_OF_FAILED_LOGINS = 3;

    UserFacade(final UserRepository userRepository,
        final RoleRepository roleRepository,
        final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(RegisterUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("User with this username already exists");
        }
        if (userRepository.existsByEmailAddress(request.getEmail())) {
            throw new BadRequestException("User with this email address already exists");
        }

        var role = roleRepository.findByCode(request.getRole())
            .orElseThrow(() -> new BadRequestException("Role provided for user doesn't exist"));

        var newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmailAddress(request.getEmail());
        newUser.setActive(true);
        newUser.setRole(role);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        userRepository.save(newUser);
    }

    public Optional<UserDto> findById(int userId) {
        return userRepository.findById(userId)
            .map(user -> UserDto.builder()
                .withId(user.getId())
                .withUsername(user.getUsername())
                .withEmail(user.getEmailAddress())
                .withActive(user.isActive())
                .withRole(user.getRole().getName())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withBlocked(user.isBlocked())
                .build()
            );
    }

    public Optional<UserSnapshot> findByUsername(String username) {
        return userRepository.findByUsername(username)
            .map(User::getSnapshot);
    }

    public void editUser(Integer id, EditUserRequest request) {
        var user = userRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("User not found"));
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmail());
        if (StringUtils.isNotBlank(request.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        userRepository.save(user);
    }

    public void disableUser(Integer id) {
        var user = userRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }

    public void handleCorrectAuthentication(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            if (!user.isBlocked()) {
                user.setNumberOfFailedLogins(0);
                userRepository.save(user);
                return;
            }
            throw new BadRequestException("Account is locked for user with username: " + user.getUsername());
        });
    }

    public void handleFailedAuthentication(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            if (user.isBlocked()) {
                throw new BadRequestException("Account is locked for user with username: " + user.getUsername());
            }
            user.setNumberOfFailedLogins(user.getNumberOfFailedLogins() + 1);
            if (user.getNumberOfFailedLogins() > MAX_NUMBER_OF_FAILED_LOGINS) {
                user.setBlocked(true);
            }
            userRepository.save(user);
        });
    }

    public void unlockAccount(Integer id) {
        userRepository.findById(id)
            .ifPresent(user -> {
                user.setBlocked(false);
                user.setNumberOfFailedLogins(0);
                userRepository.save(user);
            });
    }
}

package com.czarnecki.clinicservicesystem.user;

import com.czarnecki.clinicservicesystem.user.dto.EditUserRequest;
import com.czarnecki.clinicservicesystem.user.dto.RegisterUserRequest;
import com.czarnecki.clinicservicesystem.user.dto.UserResponse;
import com.czarnecki.clinicservicesystem.exception.BadRequestException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {

  private final UserRepository userRepository;
  private final RoleFacade roleFacade;
  private final PasswordEncoder passwordEncoder;


  private final int maxNumberOfFailedLogins = 3;

  UserFacade(final UserRepository userRepository,
             final RoleFacade roleFacade,
             final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleFacade = roleFacade;
    this.passwordEncoder = passwordEncoder;
  }

  public void registerNewUser(RegisterUserRequest request) {
    User newUser = new User();

    if (userRepository.existsByUsername(request.getUsername())) {
      throw new BadRequestException("User with this username already exists");
    }
    if (userRepository.existsByEmailAddress(request.getEmail())) {
      throw new BadRequestException("User with this email address already exists");
    }

    Role role = roleFacade.findById(request.getRole()).orElseThrow(() -> {
      throw new BadRequestException("Role provided for user doesn't exist");
    });

    newUser.setUsername(request.getUsername());
    newUser.setPassword(passwordEncoder.encode(request.getPassword()));
    newUser.setEmailAddress(request.getEmail());
    newUser.setActive(true);
    newUser.setRole(role);
    newUser.setFirstName(request.getFirstName());
    newUser.setLastName(request.getLastName());
    userRepository.save(newUser);
  }

  public Optional<User> findById(int userId) {
    return userRepository.findById(userId);
  }

  public Optional<User> findByUsername(String username) {
      return userRepository.findByUsername(username);
  }

  public List<?> getUsers() {
    Set<User> users = userRepository.findAll();
    return users
        .stream()
        .map(UserResponse::fromEntity)
        .collect(Collectors.toList());
  }

  public UserResponse getUser(Integer id) {
    User user = userRepository
        .findById(id)
        .orElseThrow(() -> new BadRequestException("User not found"));
    return UserResponse.fromEntity(user);
  }

  public void editUser(Integer id, EditUserRequest request) {
    User user = userRepository
        .findById(id)
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
    User user = userRepository
        .findById(id)
        .orElseThrow(() -> new BadRequestException("User not found"));
    user.setActive(false);
    userRepository.save(user);
  }

  public List<UserResponse> getDoctors() {
    return userRepository.findAllByRole_Code("DOC")
        .stream()
        .map(UserResponse::fromEntity)
        .collect(Collectors.toList());
  }

  public void handleCorrectAuthentication(String username) {
    userRepository.findByUsername(username).ifPresent((user) ->  {
      if (!user.isBlocked()) {
        user.setNumberOfFailedLogins(0);
        userRepository.save(user);
        return;
      }
      throw new BadRequestException("Account is locked for user with username: " + user.getUsername());
    });
  }

  public void handleFailedAuthentication(String username) {
    userRepository.findByUsername(username).ifPresent((user) -> {
      if (user.isBlocked()) {
        throw new BadRequestException("Account is locked for user with username: " + user.getUsername());
      }
      user.setNumberOfFailedLogins(user.getNumberOfFailedLogins() + 1);
      if (user.getNumberOfFailedLogins() > maxNumberOfFailedLogins) {
        user.setBlocked(true);
      }
      userRepository.save(user);
    });
  }

  public void unlockAccount(Integer id) {
    userRepository.findById(id).ifPresent((user) -> {
      user.setBlocked(false);
      user.setNumberOfFailedLogins(0);
      userRepository.save(user);
    });
  }
}

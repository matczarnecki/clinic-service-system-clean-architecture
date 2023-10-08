package com.czarnecki.clinicservicesystem.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.czarnecki.clinicservicesystem.exception.BadRequestException;
import com.czarnecki.clinicservicesystem.user.dto.RegisterUserRequest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserFacadeTest {
  @Mock
  private UserRepository mockUserRepository;
  @Mock
  private RoleFacade mockRoleFacade;
  @Mock
  private PasswordEncoder mockPasswordEncoder;

  @Test
  @DisplayName("should throw when user with given username already exists")
  void registerNewUser_usernameAlreadyExists_throwsBadRequestException() {
    // given
    var registerUserRequest = getRegisterUserMockRequest();
    when(mockUserRepository.existsByUsername(anyString())).thenReturn(true);

    var toTest = new UserFacade(mockUserRepository, null, null);

    // when
    var exception = catchThrowable(() -> toTest.registerNewUser(registerUserRequest));

    // then
    assertThat(exception)
        .isInstanceOf(BadRequestException.class)
        .hasMessage("User with this username already exists");
  }

  @Test
  @DisplayName("should throw when user with given email address already exists")
  void registerNewUser_emailAddressAlreadyExists_throwBadRequestException() {
    // given
    var registerUserRequest = getRegisterUserMockRequest();
    when(mockUserRepository.existsByUsername(anyString())).thenReturn(false);
    when(mockUserRepository.existsByEmailAddress(anyString())).thenReturn(true);

    var toTest = new UserFacade(mockUserRepository, null, null);

    // when
    var exception = catchThrowable(() -> toTest.registerNewUser(registerUserRequest));

    // then
    assertThat(exception)
        .isInstanceOf(BadRequestException.class)
        .hasMessage("User with this email address already exists");
  }

  @Test
  @DisplayName("should throw when provided role doesn't exist")
  void registerNewUser_userRoleDoesNotExist_throwBadRequestException() {
    // given
    var registerUserRequest = getRegisterUserMockRequest();
    when(mockUserRepository.existsByUsername(anyString())).thenReturn(false);
    when(mockUserRepository.existsByEmailAddress(anyString())).thenReturn(false);
    when(mockRoleFacade.findById(anyString())).thenReturn(Optional.empty());

    var toTest = new UserFacade(mockUserRepository, mockRoleFacade, null);

    // when
    var exception = catchThrowable(() -> toTest.registerNewUser(registerUserRequest));

    // then
    assertThat(exception)
        .isInstanceOf(BadRequestException.class)
        .hasMessage("Role provided for user doesn't exist");
  }

  @Test
  @DisplayName("should create new user from request")
  void registerNewUser_requestOk_createsAndSavesUser() {
    // given
    var registerUserRequest = getRegisterUserMockRequest();
    when(mockUserRepository.existsByUsername(anyString())).thenReturn(false);
    when(mockUserRepository.existsByEmailAddress(anyString())).thenReturn(false);
    when(mockRoleFacade.findById(anyString())).thenReturn(Optional.of(new Role()));
    when(mockPasswordEncoder.encode(anyString())).thenReturn("encoded");

    var toTest = new UserFacade(mockUserRepository, mockRoleFacade, mockPasswordEncoder);

    // when
    toTest.registerNewUser(registerUserRequest);

    // then
    verify(mockUserRepository).save(any(User.class));
  }

  @Test
  @DisplayName("should return optional empty when user of given id doesn't exist")
  void findById_userNotFound_returnOptionalEmpty() {
    // given
    int userId = 1;
    when(mockUserRepository.findById(anyInt())).thenReturn(Optional.empty());

    var toTest = new UserFacade(mockUserRepository, null, null);

    // when
    var result = toTest.findById(userId);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("should return optional user dto when found user with given id")
  void findById_foundUserWithId_returnOptionalUserDto() {
    // given
    int userId = 1;
    when(mockUserRepository.findById(anyInt())).thenReturn(Optional.of(new User()));

    var toTest = new UserFacade(mockUserRepository, null, null);

    // when
    var result = toTest.findById(userId);

    // then
    assertThat(result).isEmpty();
  }

  private RegisterUserRequest getRegisterUserMockRequest() {
    return new RegisterUserRequest("username", "password",
        "first name", "last name",
        "email@email.com", "role");
  }

}
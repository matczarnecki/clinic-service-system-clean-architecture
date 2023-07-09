package com.czarnecki.clinicservicesystem.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
class AppPasswordEncoder {

  @Value("${app.passwordEncoderKey}")
  private String passwordEncoderKey;

  @Bean
  PasswordEncoder passwordEncoder() {
    return new Pbkdf2PasswordEncoder(passwordEncoderKey, 100000, 32);
  }
}

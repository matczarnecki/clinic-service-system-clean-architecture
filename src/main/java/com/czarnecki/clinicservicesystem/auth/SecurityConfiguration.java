package com.czarnecki.clinicservicesystem.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] URL_WHITELIST = {
            "/v1/api/authentication",
            "/v1/api/users/registration",
            "/",
            "/static/**",
            "/favicon.ico",
            "manifest.json"
    };

//  @Value("${app.passwordEncoderKey}")
//  private String passwordEncoderKey;

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final PasswordEncoder passwordEncoder;

    SecurityConfiguration(final CustomUserDetailsService customUserDetailsService,
                          final JwtRequestFilter jwtRequestFilter,
                          final JwtAuthenticationEntryPoint unauthorizedHandler,
                          final PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.unauthorizedHandler = unauthorizedHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST)
                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//  @Bean
//  PasswordEncoder passwordEncoder() {
//    return new Pbkdf2PasswordEncoder(passwordEncoderKey, 100000, 32);
//  }
}

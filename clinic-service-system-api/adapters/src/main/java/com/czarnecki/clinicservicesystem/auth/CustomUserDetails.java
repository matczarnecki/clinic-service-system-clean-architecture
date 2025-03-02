package com.czarnecki.clinicservicesystem.auth;

import com.czarnecki.clinicservicesystem.user.vo.UserSnapshot;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final Integer id;
    private final String username;
    private final String password;
    private final String emailAddress;
    private final boolean isActive;
    private final Collection<? extends GrantedAuthority> authorities;

    CustomUserDetails(UserSnapshot user) {

        final Set<GrantedAuthority> userAuthorities =
                user.role().authorities()
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.code()))
                        .collect(Collectors.toSet());

        id = user.id();
        username = user.username();
        password = user.password();
        emailAddress = user.emailAddress();
        isActive = user.isActive();
        authorities = userAuthorities;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.czarnecki.clinicservicesystem.auth;

import com.czarnecki.clinicservicesystem.user.User;
import com.czarnecki.clinicservicesystem.user.UserFacade;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomUserDetailsService implements UserDetailsService {

    private final UserFacade userFacade;

    CustomUserDetailsService(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFacade.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return new CustomUserDetails(user);
    }
}

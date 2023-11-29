package de.shopitech.mywish.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import de.shopitech.mywish.data.entity.Benutzer;
import de.shopitech.mywish.data.repository.BenutzerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final AuthenticationContext authenticationContext;
    private final BenutzerRepository benutzerRepository;

    public AuthenticatedUser(AuthenticationContext authenticationContext, BenutzerRepository benutzerRepository) {
        this.authenticationContext = authenticationContext;
        this.benutzerRepository = benutzerRepository;
    }

    @Transactional
    public Optional<Benutzer> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(userDetails -> benutzerRepository.findBenutzerByEmail(userDetails.getUsername()).get());
    }

    public void logout() {
        authenticationContext.logout();
    }

}

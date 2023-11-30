package de.shopitech.mywish.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.spring.security.AuthenticationContext;
import de.shopitech.mywish.data.entity.Benutzer;
import de.shopitech.mywish.data.repository.BenutzerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final AuthenticationContext authenticationContext;
    private final BenutzerRepository benutzerRepository;

    public AuthenticatedUser(AuthenticationContext authenticationContext, BenutzerRepository benutzerRepository) {
        this.authenticationContext = authenticationContext;
        this.benutzerRepository = benutzerRepository;
    }

    public Optional<Benutzer> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .flatMap(userDetails -> benutzerRepository.findBenutzerByEmail(userDetails.getUsername()));
    }



    public void logout() {
        UI.getCurrent().getPage().setLocation(SecurityConfiguration.LOGOUT_URL);
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
        authenticationContext.logout();
    }
}

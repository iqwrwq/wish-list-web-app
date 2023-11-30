package de.shopitech.mywish.security;

import de.shopitech.mywish.data.entity.Benutzer;
import de.shopitech.mywish.data.model.Status;
import de.shopitech.mywish.data.repository.BenutzerRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BenutzerRepository benutzerRepository;

    public UserDetailsServiceImpl(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Benutzer> benutzer = benutzerRepository.findBenutzerByEmail(email);
        if (benutzer.isEmpty()) {
            throw new UsernameNotFoundException("No user present with email: " + email);
        } else {
            Benutzer scope = benutzer.get();
            if (Status.ACTIVE.equals(scope.getStatus())) {
                return new org.springframework.security.core.userdetails.User(
                        scope.getEmail(),
                        scope.getEncryptedPassword(),
                        getAuthorities(scope)
                );

            } else {
                throw new DisabledException("User is not approved yet");
            }
        }
    }

    private static List<GrantedAuthority> getAuthorities(Benutzer benutzer) {
        return benutzer.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

    }
}

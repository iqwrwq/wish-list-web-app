package de.shopitech.mywish.security;

import de.shopitech.mywish.data.entity.Benutzer;
import de.shopitech.mywish.data.repository.BenutzerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            throw new UsernameNotFoundException("No user present with username: " + benutzer.get().getEmail());
        } else {
            Benutzer scope = benutzer.get();

            return new org.springframework.security.core.userdetails.User(scope.getName(), scope.getPasswort(),
                    getAuthorities(scope));
        }
    }

    private static List<GrantedAuthority> getAuthorities(Benutzer benutzer) {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority("ROLE_USER"));

        return list;
    }

}

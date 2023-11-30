package de.shopitech.mywish.service;

import de.shopitech.mywish.data.entity.Benutzer;
import de.shopitech.mywish.data.model.Role;
import de.shopitech.mywish.data.repository.BenutzerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final BenutzerRepository benutzerRepository;

    public AdminService(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    public void makeUserAdmin(Long userId) {
        Optional<Benutzer> optionalUser = benutzerRepository.findById(userId);
        optionalUser.ifPresent(user -> {
            user.getRoles().add(Role.ADMIN);
            benutzerRepository.save(user);
        });
    }
}


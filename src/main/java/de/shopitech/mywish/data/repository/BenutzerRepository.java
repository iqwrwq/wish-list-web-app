package de.shopitech.mywish.data.repository;

import de.shopitech.mywish.data.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {

    Optional<Benutzer> findBenutzerByEmail(String email);
}

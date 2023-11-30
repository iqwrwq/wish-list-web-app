package de.shopitech.mywish.data.entity;

import de.shopitech.mywish.data.model.Role;
import de.shopitech.mywish.data.model.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "benutzer")
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nachname", nullable = false)
    private String nachname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @Column(name = "anmeldedatum", nullable = false)
    private Timestamp anmeldedatum;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "roles", nullable = false)
    private Set<Role> roles;
}

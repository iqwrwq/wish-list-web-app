package de.shopitech.mywish.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "benutzer")
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nachname", nullable = false)
    private String nachname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "passwort", nullable = false)
    private String passwort;

    @Column(name = "anmeldedatum", nullable = false)
    private Timestamp anmeldedatum;
}

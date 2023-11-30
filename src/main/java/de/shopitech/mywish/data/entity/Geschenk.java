package de.shopitech.mywish.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "geschenk")
public class Geschenk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "preis")
    private Double preis;

    @Column(name = "anzahl")
    private Integer anzahl;

    @Column(name = "datum", nullable = false)
    private Timestamp datum;
}

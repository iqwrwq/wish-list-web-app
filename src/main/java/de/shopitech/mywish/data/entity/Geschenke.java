package de.shopitech.mywish.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "geschenke")
public class Geschenke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "preis", precision = 10, scale = 2)
    private Double preis;

    @Column(name = "anzahl")
    private Integer anzahl;

    @Column(name = "datum", nullable = false)
    private Timestamp datum;
}


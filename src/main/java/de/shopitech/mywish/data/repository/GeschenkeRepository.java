package de.shopitech.mywish.data.repository;

import de.shopitech.mywish.data.entity.Geschenke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeschenkeRepository extends JpaRepository<Geschenke, Long> {
}

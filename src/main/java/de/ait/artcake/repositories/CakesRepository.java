package de.ait.artcake.repositories;

import de.ait.artcake.models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakesRepository extends JpaRepository<Cake, Long> {

}

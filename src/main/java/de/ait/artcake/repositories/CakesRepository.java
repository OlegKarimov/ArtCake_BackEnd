package de.ait.artcake.repositories;

import de.ait.artcake.models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CakesRepository extends JpaRepository<Cake, Long> {
    boolean existsByCategory (Cake.Category category);
    Optional<Cake> findById (Long cakeId);
}
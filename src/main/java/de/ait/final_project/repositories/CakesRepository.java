package de.ait.final_project.repositories;

import de.ait.final_project.models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakesRepository extends JpaRepository<Cake, Long> {

}

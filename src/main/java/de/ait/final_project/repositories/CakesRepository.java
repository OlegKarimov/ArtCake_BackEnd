package de.ait.final_project.repositories;

import de.ait.final_project.models.Cakes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CakesRepository extends JpaRepository<Cakes,Long> {

}

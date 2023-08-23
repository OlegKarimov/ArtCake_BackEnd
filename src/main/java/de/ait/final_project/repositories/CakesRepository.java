package de.ait.final_project.repositories;

import de.ait.final_project.models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CakesRepository extends JpaRepository<Cake,Long> {

}

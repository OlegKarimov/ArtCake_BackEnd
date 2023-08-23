package de.ait.final_project.repositories;

import de.ait.final_project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrdersRepository extends JpaRepository<Order, Long> {
}

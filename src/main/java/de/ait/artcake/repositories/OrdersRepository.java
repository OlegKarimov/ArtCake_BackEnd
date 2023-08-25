package de.ait.artcake.repositories;

import de.ait.artcake.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrdersRepository extends JpaRepository<Order, Long> {
}

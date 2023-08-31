package de.ait.artcake.repositories;

import de.ait.artcake.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByState(Order.State state, Pageable pageable);
    Page<Order> findAllByClientId(Long clientId, Pageable pageable);
    Page<Order> findAllByConfectionerId(Long confectionerId, Pageable pageable);

}

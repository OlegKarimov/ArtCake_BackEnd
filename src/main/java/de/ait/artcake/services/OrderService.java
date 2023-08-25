package de.ait.artcake.services;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderInProcessDto;


public interface OrderService {
    OrderDto addOrder(Long cakeId, NewOrderDto newOrder);

    OrderInProcessDto addOrderToProcess(Long orderId, Long confectionerId, OrderInProcessDto orderToProcess);

    OrderInProcessDto orderFinished(Long orderId, OrderInProcessDto orderToFinished);

    OrderInProcessDto getAllOrders(String orderBy, Boolean desc, String filterBy, String filterValue);
}

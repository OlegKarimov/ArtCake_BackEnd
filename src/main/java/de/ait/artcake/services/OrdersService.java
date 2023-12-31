package de.ait.artcake.services;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderForManagerDto;
import de.ait.artcake.dto.OrderInProcessDto;

public interface OrdersService {
    OrderDto addOrder(Long cakeId, NewOrderDto newOrder);

    OrderForManagerDto addOrderToProcess(Long orderId, OrderInProcessDto orderToProcess);

    OrderDto orderFinished(Long orderId);

    OrderDto orderCantFinish(Long orderId);
}

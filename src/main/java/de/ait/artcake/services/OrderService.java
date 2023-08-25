package de.ait.artcake.services;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;

public interface OrderService {
    OrderDto addOrder(Integer cakeId, NewOrderDto newOrder);
}

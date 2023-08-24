package de.ait.final_project.services;

import de.ait.final_project.dto.NewOrderDto;
import de.ait.final_project.dto.OrderDto;
import de.ait.final_project.models.Cake;
import de.ait.final_project.models.User;

public interface OrderService {
    OrderDto addOrder(Integer cakeId, NewOrderDto newOrder);
}

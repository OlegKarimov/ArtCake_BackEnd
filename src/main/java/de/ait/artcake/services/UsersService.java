package de.ait.artcake.services;


import de.ait.artcake.dto.OrdersDto;
import de.ait.artcake.dto.OrdersRequest;
import de.ait.artcake.dto.UserDto;
import de.ait.artcake.dto.UsersDto;

public interface UsersService {
    UserDto getUser(Long userId);

    UsersDto getAllUsersByRole(String role);

    OrdersDto getAllOrders(OrdersRequest request);

    OrdersDto getAllOrdersForClient(Integer pageNumber, String orderByField, Boolean desc);

    OrdersDto getAllOrdersForConfectioner(Integer pageNumber, String orderByField, Boolean desc);

}

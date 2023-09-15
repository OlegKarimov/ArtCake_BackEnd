package de.ait.artcake.services;

import de.ait.artcake.dto.*;

public interface UsersService {
    UserDto getUser(Long userId);

    UsersDto getAllUsersByRole(String role);

    OrdersForManagerDto getAllOrders(OrdersRequestDto request);

    OrdersDto getAllOrdersForClient(Integer pageNumber, String orderByField, Boolean desc);

    OrdersDto getAllOrdersForConfectioner(Integer pageNumber, String orderByField, Boolean desc);

    UserDto updateUser(Long userId, UpdateUserDto updateUser);
}

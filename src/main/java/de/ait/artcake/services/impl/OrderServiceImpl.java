package de.ait.artcake.services.impl;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderInProcessDto;
import de.ait.artcake.handler.RestException;
import de.ait.artcake.models.Cake;
import de.ait.artcake.models.Order;
import de.ait.artcake.repositories.CakesRepository;
import de.ait.artcake.repositories.OrdersRepository;
import de.ait.artcake.security.details.AuthenticatedUser;
import de.ait.artcake.services.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;




@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    OrdersRepository ordersRepository;

    CakesRepository cakesRepository;

    UsersServiceImpl usersService;

    @Transactional
    @Override
    public OrderDto addOrder(Long cakeId, NewOrderDto newOrder) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((AuthenticatedUser) authentication.getPrincipal()).id();

        Cake cake = getCakeOrThrow(cakeId);

        Order order = Order.builder()
                .count(newOrder.getCount())
                .description(newOrder.getDescription())
                .totalPrice((double)newOrder.getCount()*cake.getPrice())
                .clientId(userId)
                .cakeId(cake.getId())
                .cakeName(cake.getName())
                .state(Order.State.CREATED)
                .deadline(LocalDate.parse(newOrder.getDeadline()))
                .build();


        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    @Override
    public OrderInProcessDto addOrderToProcess(Long orderId, Long confectionerId, OrderInProcessDto orderToProcess) {

        Order order = getOrderOrThrow(orderId);

        order.setConfectionerId(confectionerId);


        order.setState(Order.State.IN_PROCESS);

        ordersRepository.save(order);

        return OrderInProcessDto.from(order);
    }

    @Override
    public OrderInProcessDto orderFinished(Long orderId, OrderInProcessDto orderToFinished) {

        Order order = getOrderOrThrow(orderId);

        order.setState(Order.State.FINISHED);

        ordersRepository.save(order);

        return OrderInProcessDto.from(order);
    }

    @Override
    public OrderInProcessDto getAllOrders(String orderBy, Boolean desc, String filterBy, String filterValue) {
        return null;
    }

    private Cake getCakeOrThrow(Long cakeId) {
        return cakesRepository.findById(cakeId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Cake with id <" + cakeId +"> not found"));
    }

    private Order getOrderOrThrow(Long orderId) {
        return ordersRepository.findById(orderId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Order with id <" + orderId + "> not found"));
    }


}

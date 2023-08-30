package de.ait.artcake.services.impl;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderInProcessDto;
import de.ait.artcake.handler.RestException;
import de.ait.artcake.models.Cake;
import de.ait.artcake.models.Order;
import de.ait.artcake.models.User;
import de.ait.artcake.repositories.OrdersRepository;
import de.ait.artcake.security.details.AuthenticatedUser;
import de.ait.artcake.services.OrdersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrdersService {

    OrdersRepository ordersRepository;

    UsersServiceImpl usersService;

    CakesServiceImpl cakesService;

    @Transactional
    @Override
    public OrderDto addOrder(Long cakeId, NewOrderDto newOrder) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((AuthenticatedUser) authentication.getPrincipal()).id();

        Cake cake = cakesService.getCakeOrThrow(cakeId);

        User user = usersService.getUserOrThrow(userId);

        Order order = Order.builder()
                .count(newOrder.getCount())
                .totalPrice((double) newOrder.getCount() * cake.getPrice())
                .state(Order.State.CREATED)
                .build();

        if (newOrder.getClientWishes() != null) {
            order.setClientWishes(newOrder.getClientWishes());
        } else {
            order.setClientWishes("Client had no wishes");
        }

        if (newOrder.getDeadline() != null) {
            order.setDeadline(LocalDate.parse(newOrder.getDeadline()));
        } else {
            order.setDeadline(LocalDate.now().plus(3, ChronoUnit.DAYS));
        }

        order.setCake(cake);
        order.setClient(user);
        order.setConfectionerId(0L);

        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    @Override
    public OrderDto addOrderToProcess(Long orderId, OrderInProcessDto orderToProcess) {

        Order order = getOrderOrThrow(orderId);

        order.setConfectionerId(orderToProcess.getConfectionerId());
        order.setState(Order.State.IN_PROCESS);

        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    @Override
    public OrderDto orderFinished(Long orderId) {

        Order order = getOrderOrThrow(orderId);

        order.setState(Order.State.FINISHED);

        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    @Override
    public OrderDto orderCantFinish(Long orderId) {

        Order order = getOrderOrThrow(orderId);

        order.setState(Order.State.CANT_FINISH);

        ordersRepository.save(order);
        return OrderDto.from(order);
    }

    private Order getOrderOrThrow(Long orderId) {
        return ordersRepository.findById(orderId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Order with id <" + orderId + "> not found"));
    }
}

package de.ait.final_project.services.impl;

import de.ait.final_project.dto.NewOrderDto;
import de.ait.final_project.dto.OrderDto;
import de.ait.final_project.handler.RestException;
import de.ait.final_project.models.Cake;
import de.ait.final_project.models.Order;
import de.ait.final_project.models.User;
import de.ait.final_project.repositories.CakesRepository;
import de.ait.final_project.repositories.OrdersRepository;
import de.ait.final_project.repositories.UsersRepository;
import de.ait.final_project.security.details.AuthenticatedUser;
import de.ait.final_project.services.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    OrdersRepository ordersRepository;

    CakesRepository cakesRepository;

    @Override
    public OrderDto addOrder(Integer cakeId, NewOrderDto newOrder) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((AuthenticatedUser) authentication.getPrincipal()).id();

        Integer user = userId.intValue();

        Cake cake = getCakeOrThrow(cakeId.longValue());

        Order order = Order.builder()
                .count(newOrder.getCount())
                .totalPrice(newOrder.getCount()*cake.getPrice())
                .clientId(user)
                .cakeId(cake.getId().intValue())
                .cakeName(cake.getName())
                .state(Order.State.CREATED)
                .build();


        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    private Cake getCakeOrThrow(Long cakeId) {
        return cakesRepository.findById(cakeId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Cake with id <" + cakeId +"> not found"));
    }


}

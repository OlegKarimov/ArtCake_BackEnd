package de.ait.artcake.services.impl;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

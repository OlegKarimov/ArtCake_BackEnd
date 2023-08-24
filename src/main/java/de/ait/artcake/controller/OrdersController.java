package de.ait.artcake.controller;

import de.ait.artcake.controller.api.OrdersApi;
import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderToProcessDto;
import de.ait.artcake.security.details.AuthenticatedUser;
import de.ait.artcake.services.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class OrdersController implements OrdersApi {

    OrderService orderService;

    @Override
    public ResponseEntity<OrderDto> orOrder(Long cakeId, AuthenticatedUser currentUser, NewOrderDto newOrder) {




        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.addOrder(cakeId.intValue(), newOrder));

    }

    @Override
    public ResponseEntity<OrderDto> orderToProcess(OrderToProcessDto orderToProcess) {
        return null;
    }


}

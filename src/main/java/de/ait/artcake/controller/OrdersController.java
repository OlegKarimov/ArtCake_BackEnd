package de.ait.artcake.controller;

import de.ait.artcake.controller.api.OrdersApi;
import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderInProcessDto;
import de.ait.artcake.services.OrdersService;
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

    OrdersService orderService;

    @Override
    public ResponseEntity<OrderDto> addOrder(Long cakeId, NewOrderDto newOrder) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.addOrder(cakeId, newOrder));

    }

    @Override
    public ResponseEntity<OrderDto> orderToProcess(Long orderId, OrderInProcessDto orderToProcess) {
        return ResponseEntity
                .ok()
                .body(orderService.addOrderToProcess(orderId, orderToProcess));
    }

    @Override
    public ResponseEntity<OrderDto> orderFinished(Long orderId) {
        return ResponseEntity
                .ok()
                .body(orderService.orderFinished(orderId));
    }

    @Override
    public ResponseEntity<OrderDto> orderCantFinish(Long orderId) {
        return ResponseEntity
                .ok()
                .body(orderService.orderCantFinish(orderId));
    }

}

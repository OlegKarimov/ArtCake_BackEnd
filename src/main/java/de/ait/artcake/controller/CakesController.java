package de.ait.artcake.controller;

import de.ait.artcake.controller.api.CakesApi;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.services.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import de.ait.artcake.dto.CakesDto;
import de.ait.artcake.services.CakesService;


@RequiredArgsConstructor
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CakesController implements CakesApi {

    CakesService cakesService;

    OrderService orderService;

    @Override
    public ResponseEntity<OrderDto> addOrder(Integer cakeId, NewOrderDto newOrder) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.addOrder(cakeId, newOrder));
    }
        @Override
        public ResponseEntity<CakesDto> getAllCakes() {
            return ResponseEntity
                    .ok()
                    .body(cakesService.getAllCakes());
        }

}

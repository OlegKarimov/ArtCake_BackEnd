package de.ait.final_project.controller;

import de.ait.final_project.controller.api.CakesApi;

import de.ait.final_project.dto.NewOrderDto;
import de.ait.final_project.dto.OrderDto;
import de.ait.final_project.security.details.AuthenticatedUser;
import de.ait.final_project.services.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


import de.ait.final_project.dto.CakesDto;
import de.ait.final_project.services.CakesService;
import de.ait.final_project.services.impl.CakesServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


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


    @Override
    public ResponseEntity<CakesDto> getAllCakes() {
        return ResponseEntity
                .ok()
                .body(cakesService.getAllCakes());
    }
}

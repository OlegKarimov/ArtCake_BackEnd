package de.ait.artcake.controller;

import de.ait.artcake.controller.api.CakesApi;

import de.ait.artcake.dto.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import de.ait.artcake.services.CakesService;
import de.ait.artcake.dto.CakesDto;

@RequiredArgsConstructor
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CakesController implements CakesApi {

    CakesService cakesService;

    @Override
    public ResponseEntity<CakeDto> addCake(NewCakeDto newCake) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cakesService.addCake(newCake));
    }

    @Override
    public ResponseEntity<CakeDto> getCake(Long cakeId) {
        return ResponseEntity
                .ok()
                .body(cakesService.getCake(cakeId));
    }

    @Override
    public ResponseEntity<CakesDto> getAllCakes(Integer pageNumber, String orderByField, Boolean desc ) {
        return ResponseEntity
                .ok()
                .body(cakesService.getAllCakes(pageNumber, orderByField, desc));
    }

    @Override
    public ResponseEntity<CakesDto> getCakesByCategory(String category) {
        return ResponseEntity
                .ok()
                .body(cakesService.getCakesByCategory(category));
    }

    @Override
    public ResponseEntity<CakeDto> updateCake(Long cakeId, UpdateCakeDto updateCake) {
        return ResponseEntity
                .ok()
                .body(cakesService.updateCake(cakeId, updateCake));
    }

    @Override
    public ResponseEntity<CakeDto> deleteCake(Long cakeId) {
        return ResponseEntity
                .ok()
                .body(cakesService.deleteCake(cakeId));
    }
}

package de.ait.final_project.controller;

import de.ait.final_project.controller.api.CakesApi;
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

    @Override
    public ResponseEntity<CakesDto> getAllCakes() {
        return ResponseEntity
                .ok()
                .body(cakesService.getAllCakes());
    }
}

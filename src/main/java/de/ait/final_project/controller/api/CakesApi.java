package de.ait.final_project.controller.api;

import de.ait.final_project.dto.CakesDto;
import de.ait.final_project.dto.StandardResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
        @Tag(name = "Cakes")
})
@RequestMapping("/api/cakes")
public interface CakesApi {

//    @Operation(summary = "Add Cake to our assortment", description = "Allowed MANAGER")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Adding successful",
//            content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = CakeDto.class))
//    })
//    })
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    ResponseEntity<CakeDto> addCake(RequestBody NewCakeDto newCake);

    @Operation(summary = "get cakes", description = "for all user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get cakes",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CakesDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    ResponseEntity<CakesDto> getAllCakes();
}


//Create spring boot API
//API GET /api/v1/products
//Return JSON Array of Products
//No filtering
//No sorting
//Database design products
//Use postman to call GET /api/v1/products which will return non empty list in JSON format.
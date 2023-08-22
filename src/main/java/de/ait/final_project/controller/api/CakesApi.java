package de.ait.final_project.controller.api;

import de.ait.final_project.dto.CakeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tags(value = {
        @Tag(name = "Cakes")
})
@RequestMapping("/api/cakes")
public interface CakesApi {

//    @Operation(summary = "Add Cake to our asortiment", description = "Allowed MANAGER")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Adding successful",
//            content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = CakeDto.class))
//    })
//    })
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    ResponseEntity<CakeDto> addCake(RequestBody NewCakeDto newCake);
}

package de.ait.artcake.controller.api;

import de.ait.artcake.dto.NewOrderDto;
import de.ait.artcake.dto.OrderDto;
import de.ait.artcake.dto.OrderToProcessDto;
import de.ait.artcake.security.details.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Orders")
})
@RequestMapping("/api/orders")
public interface OrdersApi {

    @Operation(summary = "Creating order", description = "Allowed all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    })
    })
    @PostMapping("/me/{cake-id}")
    ResponseEntity<OrderDto> orOrder(@PathVariable("cake-id") Long cakeId,
                                      @AuthenticationPrincipal AuthenticatedUser currentUser,
                                      @RequestBody @Valid NewOrderDto newOrder);

    @Operation(summary = "Giving order to process", description = "Allowed MANAGER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order in process",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    })
    })
    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping
    ResponseEntity<OrderDto> orderToProcess(@RequestBody @Valid OrderToProcessDto orderToProcess);
}

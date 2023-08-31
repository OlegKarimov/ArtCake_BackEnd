package de.ait.artcake.controller.api;

import de.ait.artcake.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        @Tag(name = "Orders")
})
@RequestMapping("/api/orders")
public interface OrdersApi {

    @Operation(summary = "Creating order", description = "Allowed all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
    })

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cakes/{cake-id}")
    ResponseEntity<OrderDto> addOrder(@Parameter(required = true, description = "Cake id", example = "1")
                                      @RequestParam @PathVariable("cake-id") Long cakeId,
                                      @RequestBody NewOrderDto newOrder);

    @Operation(summary = "Moving order to process", description = "Allowed MANAGER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Excess denied",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "200", description = "Updated order",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    })
    })
    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/{order-id}")
    ResponseEntity<OrderDto> orderToProcess(@Parameter(required = true, description = "Order id", example = "2")
                                            @RequestParam @PathVariable("order-id") Long orderId,
                                            @RequestBody OrderInProcessDto orderToProcess);

    @Operation(summary = "Moving order to finished", description = "Allowed CONFECTIONER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Excess denied",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "200", description = "Updated order",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    })
    })
    @PreAuthorize("hasAnyAuthority('CONFECTIONER')")
    @PutMapping("{order-id}/done")
    ResponseEntity<OrderDto> orderFinished(@Parameter(required = true, description = "Order id", example = "2")
                                           @RequestParam @PathVariable("order-id") Long orderId);

    @Operation(summary = "Moving order to declined", description = "Allowed CONFECTIONER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Excess denied",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "200", description = "Updated order",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrdersDto.class))
                    })
    })
    @PreAuthorize("hasAnyAuthority('CONFECTIONER')")
    @PutMapping("{order-id}/decline")
    ResponseEntity<OrderDto> orderCantFinish(@Parameter(required = true, description = "Order id", example = "2")
                                             @RequestParam @PathVariable("order-id") Long orderId);
}

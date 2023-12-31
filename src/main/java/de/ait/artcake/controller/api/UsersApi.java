package de.ait.artcake.controller.api;

import de.ait.artcake.dto.*;
import de.ait.artcake.security.details.AuthenticatedUser;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "users")
})
@RequestMapping("/api/users")
public interface UsersApi {

    @Operation(summary = "Getting profile", description = "Available to authenticated users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "User unauthorized",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/me")
    ResponseEntity<UserDto> getMyProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);

    @Operation(summary = "Getting all Users by Role", description = "only for manager")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users by Role",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "User unauthorized",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Is Forbidden",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @GetMapping("/role/{role}")
    ResponseEntity<UsersDto> getAllUsersByRole(@Parameter(required = true, description = "All all Users by Role", example = "CONFECTIONER")
                                               @PathVariable("role") String role);

    @Operation(summary = "Getting all orders as manager", description = "Only for manager allowed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrdersDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Profile is not authenticated or attempting to sort by a prohibited field",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Is Forbidden",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/manager/orders")
    ResponseEntity<OrdersForManagerDto> getAllOrders(OrdersRequestDto request);

    @Operation(summary = "Getting all orders as confectioner", description = "Only for confectioner allowed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrdersDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Profile is not authenticated or attempting to sort by a prohibited field",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Is Forbidden",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @PreAuthorize("hasAuthority('CONFECTIONER')")
    @GetMapping("/confectioner/orders")
    ResponseEntity<OrdersDto> getAllOrdersForConfectioner(@Parameter(description = "page number", example = "1")
                                                          @RequestParam(value = "page") Integer page,
                                                          @Parameter(description = "field to sort by. Available for state")
                                                          @RequestParam(value = "orderBy", required = false) String field,
                                                          @Parameter(description = "true if you want to sort in reverse order")
                                                          @RequestParam(value = "desc", required = false) Boolean desc);

    @Operation(summary = "Getting all orders as client", description = "Only for clients allowed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrdersDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Profile is not authenticated",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Is Forbidden",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/client/orders")
    ResponseEntity<OrdersDto> getAllOrdersForClient(@Parameter(description = "page number", example = "1")
                                                    @RequestParam(value = "page") Integer page,
                                                    @Parameter(description = "field to sort by. Available for state")
                                                    @RequestParam(value = "orderBy", required = false) String field,
                                                    @Parameter(description = "true if you want to sort in reverse order")
                                                    @RequestParam(value = "desc", required = false) Boolean desc);

    @Operation(summary = "Update information about user", description = "Allowed only for manager")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updating successful",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CakeDto.class))
                    }),
            @ApiResponse(responseCode = "401", description = "User unauthorized",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Is Forbidden",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @PutMapping("/{user-id}")
    ResponseEntity<UserDto> updateUser(@Parameter(required = true, description = "User id", example = "1")
                                       @PathVariable("user-id") Long userId,
                                       @Valid @RequestBody  UpdateUserDto updateUser);
}

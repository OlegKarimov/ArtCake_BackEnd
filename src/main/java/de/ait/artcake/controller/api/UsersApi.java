package de.ait.artcake.controller.api;


import de.ait.artcake.dto.*;
import de.ait.artcake.dto.OrdersDto;
import de.ait.artcake.dto.StandardResponseDto;
import de.ait.artcake.dto.UserDto;
import de.ait.artcake.dto.UsersDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @ApiResponse(responseCode = "403", description = "Profile is not authenticated",
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
            @ApiResponse(responseCode = "403", description = "forbidden operation",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Confectioners not found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @GetMapping("/role/{role}")
    ResponseEntity<UsersDto> getAllUsersByRole(@Parameter(required = true, description = "All all Users by Role", example = "CONFECTIONER")
                                               @PathVariable("role") String role);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrdersDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Profile is not authenticated",
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

  
  @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders list",
                content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrdersDto.class))
                }),
        @ApiResponse(responseCode = "403", description = "Profile is not authenticated",
                content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                })
    })
    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/client/orders")
    ResponseEntity<OrdersDto> getAllOrdersForClient(@Parameter(description = "page number", example = "1")
                                                    @RequestParam(value = "page") Integer page,
                                                    @Parameter(description = "field to sort by. Available for state")
                                                    @RequestParam(value = "orderBy", required = false) String field,
                                                    @Parameter(description = "true if you want to sort in reverse order")
                                                    @RequestParam(value = "desc", required = false) Boolean desc);

}

package de.ait.artcake.controller.api;

import de.ait.artcake.dto.StandardResponseDto;
import de.ait.artcake.dto.UserDto;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

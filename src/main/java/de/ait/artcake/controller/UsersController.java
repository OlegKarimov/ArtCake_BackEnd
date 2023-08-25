package de.ait.artcake.controller;

import de.ait.artcake.controller.api.UsersApi;
import de.ait.artcake.dto.UserDto;
import de.ait.artcake.security.details.AuthenticatedUser;
import de.ait.artcake.services.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class UsersController implements UsersApi {

    UsersService usersService;

    @Override
    public ResponseEntity<UserDto> getMyProfile(AuthenticatedUser currentUser) {
        Long userId = currentUser.id();
        return ResponseEntity.ok(usersService.getUser(userId));
    }
}

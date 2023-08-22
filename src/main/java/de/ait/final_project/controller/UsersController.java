package de.ait.final_project.controller;

import de.ait.final_project.controller.api.UsersApi;
import de.ait.final_project.dto.UserDto;
import de.ait.final_project.security.details.AuthenticatedUser;
import de.ait.final_project.services.UsersService;
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

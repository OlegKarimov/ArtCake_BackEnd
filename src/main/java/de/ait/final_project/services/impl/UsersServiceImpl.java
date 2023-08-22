package de.ait.final_project.services.impl;


import de.ait.final_project.dto.UserDto;
import de.ait.final_project.handler.RestException;
import de.ait.final_project.models.User;
import de.ait.final_project.repositories.UsersRepository;
import de.ait.final_project.services.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static de.ait.final_project.dto.UserDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    @Override
    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

    private User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "User with id <" + userId +"> not found"));
    }
}

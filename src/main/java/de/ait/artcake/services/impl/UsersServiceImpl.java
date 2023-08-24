package de.ait.artcake.services.impl;


import de.ait.artcake.dto.UserDto;
import de.ait.artcake.handler.RestException;
import de.ait.artcake.models.User;
import de.ait.artcake.repositories.UsersRepository;
import de.ait.artcake.services.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static de.ait.artcake.dto.UserDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    @Override
    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

     User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "User with id <" + userId +"> not found"));
    }
}

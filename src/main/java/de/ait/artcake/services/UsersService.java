package de.ait.artcake.services;


import de.ait.artcake.dto.UserDto;

public interface UsersService {
    UserDto getUser(Long userId);
}

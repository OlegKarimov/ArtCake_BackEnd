package de.ait.artcake.services;


import de.ait.artcake.dto.UserDto;
import de.ait.artcake.dto.UsersDto;

public interface UsersService {
    UserDto getUser(Long userId);

    UsersDto getAllUsersByRole(String role);
}

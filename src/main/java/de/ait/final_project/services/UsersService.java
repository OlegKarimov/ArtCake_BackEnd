package de.ait.final_project.services;


import de.ait.final_project.dto.UserDto;

public interface UsersService {
    UserDto getUser(Long userId);
}

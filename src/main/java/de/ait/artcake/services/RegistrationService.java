package de.ait.artcake.services;


import de.ait.artcake.dto.RegisterDto;
import de.ait.artcake.dto.UserDto;

public interface RegistrationService {
    UserDto register(RegisterDto registerData);
}

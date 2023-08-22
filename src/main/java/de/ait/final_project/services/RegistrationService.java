package de.ait.final_project.services;


import de.ait.final_project.dto.RegisterDto;
import de.ait.final_project.dto.UserDto;

public interface RegistrationService {
    UserDto register(RegisterDto registerData);
}

package de.ait.final_project.services.impl;


import de.ait.final_project.dto.RegisterDto;
import de.ait.final_project.dto.UserDto;
import de.ait.final_project.models.User;
import de.ait.final_project.repositories.UsersRepository;
import de.ait.final_project.services.RegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static de.ait.final_project.dto.UserDto.from;



@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    UsersRepository usersRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(RegisterDto registerData) {
        User user = User.builder()
                .fullName(registerData.getFullName())
                .email(registerData.getEmail())
                .hashPassword(passwordEncoder.encode(registerData.getPassword()))
                .Address(registerData.getAddress())
                .phoneNumber(registerData.getPhoneNumber())
                .role(User.Role.CLIENT)
                .state(User.State.CONFIRMED).build();

        usersRepository.save(user);

        return from(user);
    }
}

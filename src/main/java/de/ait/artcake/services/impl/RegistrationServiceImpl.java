package de.ait.artcake.services.impl;


import de.ait.artcake.dto.RegisterDto;
import de.ait.artcake.dto.UserDto;
import de.ait.artcake.models.User;
import de.ait.artcake.repositories.UsersRepository;
import de.ait.artcake.services.RegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static de.ait.artcake.dto.UserDto.from;



@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    UsersRepository usersRepository;

    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto register(RegisterDto registerData) {
        User user = User.builder()
                .fullName(registerData.getFullName())
                .email(registerData.getEmail())
                .hashPassword(passwordEncoder.encode(registerData.getPassword()))
                .town(registerData.getTown())
                .street(registerData.getStreet())
                .houseNumber(registerData.getHouseNumber())
                .phoneNumber(registerData.getPhoneNumber())
                .role(User.Role.CLIENT)
                .state(User.State.CONFIRMED).build();

        usersRepository.save(user);

        return from(user);
    }
}

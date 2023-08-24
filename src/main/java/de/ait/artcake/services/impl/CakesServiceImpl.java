package de.ait.artcake.services.impl;

import de.ait.artcake.dto.CakesDto;
import de.ait.artcake.repositories.CakesRepository;
import de.ait.artcake.services.CakesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static de.ait.artcake.dto.CakeDto.from;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class CakesServiceImpl implements CakesService {

    CakesRepository cakesRepository;
    @Override
    public CakesDto getAllCakes() {
        return CakesDto.builder()
                .cakes(from(cakesRepository.findAll()))
                .build();
    }
}

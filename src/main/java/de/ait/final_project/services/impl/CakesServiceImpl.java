package de.ait.final_project.services.impl;

import de.ait.final_project.dto.CakesDto;
import de.ait.final_project.repositories.CakesRepository;
import de.ait.final_project.services.CakesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static de.ait.final_project.dto.CakeDto.from;

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

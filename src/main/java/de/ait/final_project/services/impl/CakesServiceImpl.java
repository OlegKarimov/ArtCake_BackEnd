package de.ait.final_project.services.impl;

import de.ait.final_project.dto.CakeDto;
import de.ait.final_project.dto.CakesDto;
import de.ait.final_project.dto.NewCakeDto;
import de.ait.final_project.dto.UpdateCakeDto;
import de.ait.final_project.handler.RestException;
import de.ait.final_project.models.Cake;
import de.ait.final_project.repositories.CakesRepository;
import de.ait.final_project.services.CakesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static de.ait.final_project.dto.CakeDto.from;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class CakesServiceImpl implements CakesService {

    CakesRepository cakesRepository;

    @Override
    public CakeDto addCake(NewCakeDto newCake) {

        Cake cake = Cake.builder()
                .name(newCake.getName())
                .ingredients(newCake.getIngredients())
                .price(newCake.getPrice())
                .category(Cake.Category.valueOf(newCake.getCategory()))
                .build();

        cakesRepository.save(cake);

        return CakeDto.from(cake);
    }

    @Override
    public CakeDto updateCake(Long cakeId, UpdateCakeDto updateCake) {

        Cake cake = getCakeOrThrow(cakeId);

        cake.setName(updateCake.getName());
        cake.setIngredients(updateCake.getIngredients());
        cake.setPrice(updateCake.getPrice());

        cakesRepository.save(cake);

        return CakeDto.from(cake);
    }

    @Override
    public CakeDto deleteCake(Long cakeId) {

        Cake cake = getCakeOrThrow(cakeId);

        cakesRepository.delete(cake);

        return from(cake);
    }

    @Override
    public CakesDto getAllCakes() {
        return CakesDto.builder()
                .cakes(from(cakesRepository.findAll()))
                .build();
    }

    @Override
    public CakeDto getCake(Long cakeId) {
        return CakeDto.from(getCakeOrThrow(cakeId));
    }

    Cake getCakeOrThrow(Long cakeId) {
        return cakesRepository.findById(cakeId)
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND,"Cake with id <"+ cakeId + "> not found"));
    }
}

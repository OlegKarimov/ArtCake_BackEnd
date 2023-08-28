package de.ait.artcake.services.impl;

import de.ait.artcake.dto.CakeDto;
import de.ait.artcake.dto.CakesDto;
import de.ait.artcake.dto.NewCakeDto;
import de.ait.artcake.dto.UpdateCakeDto;
import de.ait.artcake.handler.RestException;
import de.ait.artcake.models.Cake;

import de.ait.artcake.repositories.CakesRepository;
import de.ait.artcake.services.CakesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static de.ait.artcake.dto.CakeDto.from;
import static de.ait.artcake.dto.CakeDto.fromByCategory;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class CakesServiceImpl implements CakesService {

    CakesRepository cakesRepository;
  
    @Transactional
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

    @Transactional
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
    public CakeDto getCake(Long cakeId)
    {
        return CakeDto.from(getCakeOrThrow(cakeId));
    }

    Cake getCakeOrThrow(Long cakeId) {
        return cakesRepository.findById(cakeId)
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND,"Cake with id <"+ cakeId + "> not found"));
    }
    @Override
    public CakesDto getCakesByCategory(String category)
    {
        CakesDto returnCakes = CakesDto.builder()
            .cakes(fromByCategory(cakesRepository.findAll(), category))
            .build();
        if (!returnCakes.getCakes().isEmpty()){
            return returnCakes;
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "Category <" + category + "> not found");
        }
    }

}

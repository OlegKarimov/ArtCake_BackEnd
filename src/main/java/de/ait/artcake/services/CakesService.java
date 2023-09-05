package de.ait.artcake.services;

import de.ait.artcake.dto.CakeDto;
import de.ait.artcake.dto.CakesDto;
import de.ait.artcake.dto.NewCakeDto;
import de.ait.artcake.dto.UpdateCakeDto;

public interface CakesService {

    CakesDto getAllCakes(Integer pageNumber, String orderByField, Boolean desc);

    CakeDto getCake(Long cakeId);

    CakeDto addCake(NewCakeDto newCake);

    CakeDto updateCake(Long cakeId, UpdateCakeDto updateCake);

    CakesDto getCakesByCategory(String category);
}

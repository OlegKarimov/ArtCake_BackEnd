package de.ait.artcake.services;

import de.ait.artcake.dto.*;

import java.util.List;

public interface CakesService {

    CakesDto getAllCakes(Integer pageNumber, String orderByField, Boolean desc);

    CakeDto getCake(Long cakeId);

    CakeDto addCake(NewCakeDto newCake);

    CakeDto updateCake(Long cakeId, UpdateCakeDto updateCake);

    CakesDto getCakesByCategory(String category);

    List<CakeRatingDto> getCakeSales();
}

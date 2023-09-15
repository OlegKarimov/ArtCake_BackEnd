package de.ait.artcake.services;

import de.ait.artcake.dto.*;

import java.util.List;

public interface CakesService {

    GetCakesImageDto getAllCakes(Integer pageNumber, String orderByField, Boolean desc);

    GetCakeImageDto getCake(Long cakeId);

    CakeDto addCake(NewCakeDto newCake);

    CakeDto updateCake(Long cakeId, UpdateCakeDto updateCake);

    GetCakesImageDto getCakesByCategory(String category);

    List<CakeRatingDto> getCakeSales();
}

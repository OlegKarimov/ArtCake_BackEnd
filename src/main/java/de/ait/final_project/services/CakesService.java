package de.ait.final_project.services;

import de.ait.final_project.dto.CakeDto;
import de.ait.final_project.dto.CakesDto;
import de.ait.final_project.dto.NewCakeDto;
import de.ait.final_project.dto.UpdateCakeDto;

public interface CakesService {

    CakesDto getAllCakes();

    CakeDto getCake(Long cakeId);

    CakeDto addCake(NewCakeDto newCake);

    CakeDto updateCake(Long cakeId, UpdateCakeDto updateCake);

    CakeDto deleteCake(Long cakeId);
}

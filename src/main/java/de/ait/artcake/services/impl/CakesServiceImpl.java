package de.ait.artcake.services.impl;

import de.ait.artcake.dto.CakeDto;
import de.ait.artcake.dto.CakesDto;
import de.ait.artcake.dto.NewCakeDto;
import de.ait.artcake.dto.UpdateCakeDto;
import de.ait.artcake.handler.RestException;
import de.ait.artcake.models.Cake;

import de.ait.artcake.repositories.CakesRepository;
import de.ait.artcake.services.CakesService;
import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static de.ait.artcake.dto.CakeDto.from;
import static de.ait.artcake.dto.CakeDto.fromByCategory;

@Service
@RequiredArgsConstructor
public class CakesServiceImpl implements CakesService {

    private final CakesRepository cakesRepository;

    @Value("${spring.application.sort.cake.fields}")
    private final List<String> sortFields;

    @Value("${spring.application.cake.page.size}")
    private Integer pageSize;
  
    @Transactional
    @Override
    public CakeDto addCake(NewCakeDto newCake) {

        Cake cake = Cake.builder()
                .name(newCake.getName())
                .ingredients(newCake.getIngredients())
                .price(newCake.getPrice())
                .category(Cake.Category.valueOf(newCake.getCategory()))
                .state(Cake.State.valueOf(newCake.getState()))
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
        cake.setState(Cake.State.valueOf(updateCake.getState()));

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
    public CakesDto getAllCakes(Integer pageNumber, String orderByField, Boolean desc) {

        PageRequest pageRequest = getPageRequest(pageNumber, orderByField, desc);

        Page<Cake> page = cakesRepository.findAll(pageRequest);

        return CakesDto.builder()
                .cakes(from(page.getContent()))
                .count(page.getTotalElements())
                .pagesCount(page.getTotalPages())
                .build();
    }

    private PageRequest getPageRequest(Integer pageNumber, String orderByField, Boolean desc) {
        if(orderByField != null && !orderByField.equals("")) {

            checkSortField(orderByField);
            Sort.Direction direction = Sort.Direction.ASC;

            if (desc != null && desc) {
                direction = Sort.Direction.DESC;
            }
            Sort sort = Sort.by(direction, orderByField);
            return PageRequest.of(pageNumber, pageSize, sort);
        } else {
            return PageRequest.of(pageNumber, pageSize);
        }
    }

    private void checkSortField(String field) {
        if(!sortFields.contains(field)) {
            throw new RestException(HttpStatus.FORBIDDEN, "Can not sort <" + field + ">" );
        }
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

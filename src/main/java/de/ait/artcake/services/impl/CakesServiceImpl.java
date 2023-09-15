package de.ait.artcake.services.impl;

import de.ait.artcake.dto.*;
import de.ait.artcake.handler.RestException;
import de.ait.artcake.models.Cake;

import de.ait.artcake.models.Order;
import de.ait.artcake.repositories.CakesRepository;
import de.ait.artcake.repositories.OrdersRepository;
import de.ait.artcake.services.CakesService;
import lombok.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.ait.artcake.dto.GetCakeImageDto.from;
import static de.ait.artcake.dto.GetCakeImageDto.fromByCategory;

@Service
@RequiredArgsConstructor
public class CakesServiceImpl implements CakesService {

    private final CakesRepository cakesRepository;

    private final OrdersRepository ordersRepository;

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

    @Override
    public GetCakesImageDto getAllCakes(Integer pageNumber, String orderByField, Boolean desc) {

        PageRequest pageRequest = getPageRequest(pageNumber, orderByField, desc);

        Page<Cake> page = cakesRepository.findAll(pageRequest);

        return GetCakesImageDto.builder()
                .cakes(from(page.getContent()))
                .count(page.getTotalElements())
                .pagesCount(page.getTotalPages())
                .build();
    }

    private PageRequest getPageRequest(Integer pageNumber, String orderByField, Boolean desc) {
        if (orderByField != null && !orderByField.equals("")) {

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
        if (!sortFields.contains(field)) {
            throw new RestException(HttpStatus.FORBIDDEN, "Can not sort <" + field + ">");
        }
    }

    @Override
    public GetCakeImageDto getCake(Long cakeId) {
        return GetCakeImageDto.from(getCakeOrThrow(cakeId));
    }


    Cake getCakeOrThrow(Long cakeId) {
        return cakesRepository.findById(cakeId)
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND, "Cake with id <" + cakeId + "> not found"));
    }

    @Override
    public GetCakesImageDto getCakesByCategory(String category) {
        GetCakesImageDto returnCakes = GetCakesImageDto.builder()
                .cakes(fromByCategory(cakesRepository.findAll(), category))
                .build();
        if (!returnCakes.getCakes().isEmpty()) {
            return returnCakes;
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "Category <" + category + "> not found");
        }
    }

    @Override
    public List<CakeRatingDto> getCakeSales() {
        List<Order> finishedOrders = ordersRepository.findByState(Order.State.FINISHED);
        Map<Long, Map<Long, Integer>> cakeRatingMap = new HashMap<>();

        for (Order order : finishedOrders) {
            Long cakeId = order.getCake().getId();
            Long orderId = order.getId();
            int quantity = order.getCount();

            cakeRatingMap.putIfAbsent(cakeId, new HashMap<>());

            Map<Long, Integer> orderQuantityMap = cakeRatingMap.get(cakeId);
            orderQuantityMap.put(orderId, quantity);
        }

        return cakeRatingMap.entrySet()
                .stream()
                .map(cakeEntry -> {
                    Long cakeId = cakeEntry.getKey();
                    Map<Long, Integer> orderQuantityMap = cakeEntry.getValue();

                    int numberOfSales = orderQuantityMap.size();
                    int totalQuantity = orderQuantityMap.values().stream().mapToInt(Integer::intValue).sum();

                    Cake cake = getCakeById(cakeId);

                    if (cake.getState() != Cake.State.DELETED) {
                        return createCakeRating(cakeId, numberOfSales, totalQuantity);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .sorted((a, b) -> Integer.compare(b.getTotalQuantity(), a.getTotalQuantity()))
                .limit(5)
                .collect(Collectors.toList());
    }

    private CakeRatingDto createCakeRating(Long cakeId, int numberOfSales, int totalQuantity) {
        CakeRatingDto cakeRatingDto = new CakeRatingDto();
        cakeRatingDto.setCakeId(cakeId);
        cakeRatingDto.setNumberOfSales(numberOfSales);
        cakeRatingDto.setTotalQuantity(totalQuantity);

        return cakeRatingDto;
    }

    private Cake getCakeById(Long cakeId) {
        return cakesRepository.findById(cakeId).orElse(null);
    }

}

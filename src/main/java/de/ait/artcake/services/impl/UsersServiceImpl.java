package de.ait.artcake.services.impl;

import de.ait.artcake.dto.*;
import de.ait.artcake.handler.RestException;
import de.ait.artcake.models.Order;
import de.ait.artcake.models.User;
import de.ait.artcake.repositories.OrdersRepository;
import de.ait.artcake.repositories.UsersRepository;
import de.ait.artcake.security.details.AuthenticatedUser;
import de.ait.artcake.services.UsersService;
import de.ait.artcake.utils.PageRequestsUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.artcake.dto.UserDto.from;
import static de.ait.artcake.dto.UserDto.fromByRole;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    OrdersRepository ordersRepository;

    PageRequestsUtil pageRequestsUtil;

    @Value("${spring.application.sort.order.fields}")
    List<String> sortFields;

    @Value("${spring.application.filter.order.fields}")
    List<String> filterFields;

    @Override
    public UserDto getUser(Long userId) {

        return from(getUserOrThrow(userId));
    }

    @Override
    public UsersDto getAllUsersByRole(String role) {
        UsersDto returnConfectioners = UsersDto.builder()
                .users(fromByRole(usersRepository.findAll(), role))
                .build();
        if (!returnConfectioners.getUsers().isEmpty()) {
            return returnConfectioners;
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "User with Role <" + role + "> not found");
        }
    }

    @Override
    public OrdersForManagerDto getAllOrders(OrdersRequestDto request) {

        PageRequest pageRequest = pageRequestsUtil.getPageRequest(request.getPage(), request.getOrderBy(), request.getDesc(), sortFields);
        Page<Order> page = getOrdersPage(request.getFilterBy(), request.getFilterValue(), pageRequest);

        return OrdersForManagerDto.builder()
                .orders(OrderForManagerDto.from(page.getContent()))
                .count(page.getTotalElements())
                .pagesCount(page.getTotalPages())
                .build();
    }

    private Page<Order> getOrdersPage(String filterBy, String filterValue, PageRequest pageRequest) {
        Page<Order> page = Page.empty();
        if (filterBy == null || filterBy.isEmpty()) {
            return ordersRepository.findAll(pageRequest);
        } else {
            pageRequestsUtil.checkField(filterFields, filterBy);
            if (filterBy.equals("client")) {
                try {
                    page = ordersRepository.findAllByClientId(Long.parseLong(filterValue), pageRequest);
                } catch (NumberFormatException e) {
                    throw new RestException(HttpStatus.NOT_FOUND, "<" + filterValue + ">is wrong, enter the filter value as a number.");
                }
            }
            if (filterBy.equals("confectionerId")) {
                try {
                    page = ordersRepository.findAllByConfectionerId(Long.parseLong(filterValue), pageRequest);
                } catch (NumberFormatException e) {
                    throw new RestException(HttpStatus.NOT_FOUND, "<" + filterValue + ">is wrong, enter the filter value as a number.");
                }
            } else if (filterBy.equals("state")) {
                Order.State state = Order.State.valueOf(filterValue);
                page = ordersRepository.findAllByState(state, pageRequest);
            }
        }
        return page;
    }

    @Override
    public OrdersDto getAllOrdersForClient(Integer pageNumber, String orderByField, Boolean desc) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long clientId = ((AuthenticatedUser) authentication.getPrincipal()).id();

        PageRequest pageRequest = getPageRequest(pageNumber, orderByField, desc);

        Page<Order> page = ordersRepository.findAll(pageRequest);

        return OrdersDto.builder()
                .orders(OrderDto.fromByClient(page.getContent(), clientId))
                .count((long) page.stream().filter(order -> order.getClient().getId().equals(clientId)).toList().size())
                .pagesCount(page.getTotalPages())
                .build();
    }

    @Override
    public OrdersDto getAllOrdersForConfectioner(Integer pageNumber, String orderByField, Boolean desc) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long confectionerId = ((AuthenticatedUser) authentication.getPrincipal()).id();

        PageRequest pageRequest = getPageRequest(pageNumber, orderByField, desc);

        Page<Order> page = ordersRepository.findAll(pageRequest);

        return OrdersDto.builder()
                .orders(OrderDto.fromByConfectioner(page.getContent(), confectionerId))
                .count((long) page.stream().filter(order -> order.getConfectionerId().equals(confectionerId)).toList().size())
                .pagesCount(page.getTotalPages())
                .build();
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto updateUser) {

        User user = getUserOrThrow(userId);

        user.setTown(updateUser.getTown());
        user.setStreet(updateUser.getStreet());
        user.setHouseNumber(updateUser.getHouseNumber());
        user.setZipCode(updateUser.getZipCode());
        user.setPhoneNumber(updateUser.getPhoneNumber());

        if (updateUser.getState().equalsIgnoreCase(User.State.NOT_CONFIRMED.name()) ||
                updateUser.getState().equalsIgnoreCase(User.State.CONFIRMED.name()) ||
                updateUser.getState().equalsIgnoreCase(User.State.BANNED.name())) {
            user.setState(User.State.valueOf(updateUser.getState()));
        } else {
            throw new RestException(HttpStatus.FORBIDDEN, "Impossible to set <" + updateUser.getState() + "> " +
                    "as user state. Available states: NOT_CONFIRMED, CONFIRMED and BANNED");
        }

        if (updateUser.getRole().equalsIgnoreCase(User.Role.CONFECTIONER.name()) ||
                updateUser.getRole().equalsIgnoreCase(User.Role.CLIENT.name())) {
            user.setRole(User.Role.valueOf(updateUser.getRole()));
        } else {
            throw new RestException(HttpStatus.FORBIDDEN, "Impossible to set <" + updateUser.getRole() + "> " +
                    "as user role. Available roles: CONFECTIONER or CLIENT");
        }

        usersRepository.save(user);

        return UserDto.from(user);
    }

    private PageRequest getPageRequest(Integer pageNumber, String orderByField, Boolean desc) {
        Integer pageSize = 10;

        if (orderByField != null && !orderByField.equals("")) {

            pageRequestsUtil.checkField(sortFields, orderByField);
            Sort.Direction direction = Sort.Direction.ASC;

            if (desc != null && desc) {
                direction = Sort.Direction.DESC;
            }
            Sort sort = Sort.by(direction, orderByField);
            return PageRequest.of(pageNumber, pageSize, sort);
        } else {
            return pageRequestsUtil.getDefaultPageRequest(pageNumber, pageSize);
        }
    }

    User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "User with id <" + userId + "> not found"));
    }
}

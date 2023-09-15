package de.ait.artcake.dto;

import de.ait.artcake.models.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderForManagerDto {

    @Schema(description = "Cake Identifier", example = "1")
    private Long id;

    @Schema(description = "The cake ordered by the client", example = "1")
    private CakeDto cake;

    @Schema(description = "Quantity of cakes", example = "1")
    private Integer count;

    @Schema(description = "Client wishes", example = "Make in blue and white colours")
    private String clientWishes;

    @Schema(description = "Total cost of order", example = "120 EUR")
    private Double totalPrice;

    @Schema(description = "Order creation date", example = "2023-02-02")
    private String creationDate;

    @Schema(description = "Confectioner Identifier", example = "1")
    private Long confectionerId;

    @Schema(description = "Deadline date ", example = "2023-02-02")
    private String deadline;

    @Schema(description = "The client who placed the order")
    private UserDto client;

    @Schema(description = "Order state, can be: CREATED, IN_PROCESS, CANT_FINISH, FINISHED ", example = "CREATED")
    private String state;

    public static OrderForManagerDto from(Order order) {
        return OrderForManagerDto.builder()
                .id(order.getId())
                .cake(CakeDto.from(order.getCake()))
                .count(order.getCount())
                .clientWishes(order.getClientWishes())
                .deadline(order.getDeadline().toString())
                .totalPrice(order.getTotalPrice())
                .creationDate(order.getCreationDate().toString())
                .confectionerId(order.getConfectionerId())
                .client(UserDto.from(order.getClient()))
                .state(order.getState().name())
                .build();
    }

    public static List<OrderForManagerDto> from(List<Order> orders) {
        return orders.stream()
                .map(OrderForManagerDto::from)
                .collect(Collectors.toList());
    }
}

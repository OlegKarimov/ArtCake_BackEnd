package de.ait.artcake.dto;

import de.ait.artcake.models.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    @Schema(description = "Cake Identifier", example = "1")
    private Long id;

    @Schema(description = "Cake id", example = "1")
    private CakeDto cake;

    @Schema(description = "Quantity of cakes", example = "1")
    private Integer count;

    @Schema(description = "Client wishes", example = "Make in blue and white colours")
    private String clientWishes;

    @Schema(description = "Total cost of order", example = "120 EUR")
    private Double totalPrice;

    @Schema(description = "Order creation date", example = "2023-02-02")
    private String creationDate;

    @Schema(description = "Deadline date ", example = "2023-02-02")
    private String deadline;

    @Schema(description = "Order state, can be: CREATED, IN_PROCESS, CANT_FINISH, FINISHED ", example = "CREATED")
    private String state;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .cake(CakeDto.from(order.getCake()))
                .count(order.getCount())
                .clientWishes(order.getClientWishes())
                .deadline(order.getDeadline().toString())
                .totalPrice(order.getTotalPrice())
                .creationDate(order.getCreationDate().toString())
                .state(order.getState().name())
                .build();
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }

    public static List<OrderDto> fromByClient(Collection<Order> orders, Long clientId) {
        return orders.stream()
                .filter(order -> order.getClient().getId().equals(clientId))
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }

    public static List<OrderDto> fromByConfectioner(Collection<Order> orders, Long confectionerId) {
        return orders.stream()
                .filter(order -> order.getConfectionerId().equals(confectionerId))
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }
}

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
public class OrderDto {

    @Schema(description = "Cake Identifier", example = "1")
    private Long id;

    @Schema(description = "Cakes name", example = "Kievskiy")
    private String cakeName;

    @Schema(description = "Cake id", example = "1")
    private Integer cakeId;

    @Schema(description = "Quantity of cakes", example = "1")
    private Double count;

    @Schema(description = "Client wishes", example = "Make in blue and white colours")
    private String description;

    @Schema(description = "Total cost of order", example = "120 EUR")
    private Double totalPrice;

    @Schema(description = "Order creation date", example = "2023-02-02")
    private String creationDate;

    @Schema(description = "Deadline date ", example = "2023-02-02")
    private String deadline;

    public static OrderDto from(Order order) {
        OrderDto result = OrderDto.builder()
                .id(order.getId())
                .cakeName(order.getCakeName())
                .cakeId(order.getCakeId())
                .count(order.getCount())
                .totalPrice(order.getTotalPrice())
                .creationDate(order.getCreationDate().toString())
                .build();

        if(order.getDescription() != null){
            result.setDescription(order.getDescription());
        }

        if(order.getDeadline() != null){
            result.setDeadline(order.getDeadline().toString());
        }

        return result;
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }
}

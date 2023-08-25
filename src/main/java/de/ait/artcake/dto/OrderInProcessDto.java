package de.ait.artcake.dto;

import de.ait.artcake.models.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInProcessDto {
    @Schema(description = "Cake Identifier", example = "1")
    private Long id;

    @Schema(description = "Cake id", example = "1")
    private Long cakeId;

    @Schema(description = "Cakes name", example = "Kievskiy")
    private String cakeName;

    @Schema(description = "Quantity of cakes", example = "1")
    private Integer count;

    @Schema(description = "Client wishes", example = "Make in blue and white colours")
    private String description;

    @Schema(description = "Deadline date", example = "2023-10-10")
    private String deadLine;

    @Schema(description = "Confectioner Id of the confectioner who declined", example = "3")
    private Long confectionerId;

    @Schema(description = "Confectioner Id", example = "3")
    private Long declinedConfectionerId;

    private String state;

    public static OrderInProcessDto from(Order order){
            OrderInProcessDto result = OrderInProcessDto.builder()
                .id(order.getId())
                .cakeId(order.getCakeId())
                .cakeName(order.getCakeName())
                .count(order.getCount())
                .description(order.getDescription())
                .confectionerId(order.getConfectionerId())
                .deadLine(order.getDeadline().toString())
                .state(order.getState().name())
                .build();

            return result;

    }


}

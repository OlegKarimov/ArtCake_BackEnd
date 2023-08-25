package de.ait.artcake.dto;

import de.ait.artcake.models.Cake;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Infromation about Cake in order")
public class CakeInOrderDto {

    @Schema(description = "ID of cake", example = "1")
    private Long id;

    @Schema(description = "name of cake", example = "Kievskiy")
    private String cakeName;

    @Schema(description = "Cakes price", example = "50 EUR")
    private Double price;

    public static CakeInOrderDto from(Cake cake) {
        return CakeInOrderDto.builder()
                .id(cake.getId())
                .cakeName(cake.getName())
                .price(cake.getPrice())
                .build();
    }
}

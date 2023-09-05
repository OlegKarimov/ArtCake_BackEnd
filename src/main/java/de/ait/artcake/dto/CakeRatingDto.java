package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CakeRatingDto {

    @Schema(description = "Cake id", example = "1")
    private Long cakeId;

    @Schema(description = "how many times ordered", example = "1")
    private int numberOfSales;

    @Schema(description = "how many ordered", example = "6")
    private int totalQuantity;
}

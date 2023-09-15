package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDto {

    @Schema(description = "Orders list")
    private List<OrderDto> orders;

    @Schema(description = "Total number of orders", example = "2")
    private Long count;

    @Schema(description = "total number of pages", example = "1")
    private Integer pagesCount;

}

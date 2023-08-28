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
@Schema(description = "List of all Cakes")
public class CakesDto {

    @Schema(description = "Our Cakes")
    private List<CakeDto> cakes;

    @Schema(description = "Total number of cakes", example = "20")
    private Long count;

    @Schema(description = "total number of pages", example = "4")
    private Integer pagesCount;

}

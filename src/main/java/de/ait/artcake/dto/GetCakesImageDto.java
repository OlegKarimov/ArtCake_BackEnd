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
@Schema(description = "List of all Cakes with Images")
public class GetCakesImageDto {

    @Schema(description = "Our Cakes with Images")
    private List<GetCakeImageDto> cakes;

    @Schema(description = "Total number of cakes with Images", example = "20")
    private Long count;

    @Schema(description = "total number of pages with Images", example = "4")
    private Integer pagesCount;
}

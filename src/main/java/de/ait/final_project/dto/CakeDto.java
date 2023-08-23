package de.ait.final_project.dto;

import de.ait.final_project.models.Cakes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CakeDto {

    @Schema(description = "Cakes id", example = "1")
    private Long id;

    @Schema(description = "Cakes name", example = "Cupcake")
    private String name;

    @Schema(description = "Cakes ingredients", example = "1 egg, 100g milk")
    private String ingredients;

    @Schema(description = "Cakes price", example = "100$")
    private String price;

    @Schema(description = "Cakes Category", example = "1")
    private String category;

    @Schema(description = "linking a cake to ordersId", example = "1")
    private Long ordersId;

    public static CakeDto from (Cakes cake) {
        CakeDto result = CakeDto.builder()
                .id(cake.getId())
                .name(cake.getName())
                .ingredients(cake.getName())
                .category(cake.getCategory().name())
                .build();

        if (cake.getPrice() != null){
            result.setPrice(cake.getPrice().toString());
        }

        return result;
    }

    public static List<CakeDto> from(Collection<Cakes> cakes){
        return cakes.stream()
                .map(CakeDto::from)
                .collect(Collectors.toList());
    }
}

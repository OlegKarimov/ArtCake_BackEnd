package de.ait.artcake.dto;

import de.ait.artcake.models.Cake;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Cake with Images")
public class GetCakeImageDto {

    @Schema(description = "Cakes id", example = "1")
    private Long id;

    @Schema(description = "Cakes name", example = "BlueBerry-Cupcake")
    private String name;

    @Schema(description = "Cakes ingredients", example = "eggs, milk, salt")
    private String ingredients;

    @Schema(description = "Cakes price", example = "100.00")
    private Double price;

    @Schema(description = "Cakes Category", example = "CUPCAKES")
    private String category;

    @Schema(description = "Cake Image path")
    private String imagePath;

    public static GetCakeImageDto from (Cake cake) {
        return GetCakeImageDto.builder()
                .id(cake.getId())
                .name(cake.getName())
                .ingredients(cake.getIngredients())
                .price(cake.getPrice())
                .category(cake.getCategory().name())
                .imagePath(cake.getImagePath())
                .build();
    }

    public static List<GetCakeImageDto> from(Collection<Cake> cakes){
        return cakes.stream()
                .map(GetCakeImageDto::from)
                .collect(Collectors.toList());
    }

    public static List<GetCakeImageDto> fromByCategory(Collection<Cake> cakes, String category) {
        return cakes.stream()
                .filter(cake -> cake.getCategory().toString().equalsIgnoreCase(category))
                .map(GetCakeImageDto::from)
                .collect(Collectors.toList());
    }
}

package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Adding a cake")
public class NewCakeDto {

    @NotBlank
    @Schema(description = "cake name", example = "blueberry-cupcake")
    private String name;

    @NotBlank
    @Schema(description = "cake ingredients", example = "milk,egg,salt...")
    private String ingredients;

    @NotBlank
    @Schema(description = "cake price", example = "70.50")
    private Double price;

    @NotBlank
    @Schema(description = "cake category", example = "CUPCAKE")
    private String category;

}

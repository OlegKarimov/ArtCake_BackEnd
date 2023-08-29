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

    @Schema(description = "Confectioner Id of the confectioner who declined", example = "3")
    private Long confectionerId;

}

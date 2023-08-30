package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Orders list")
public class OrdersRequestDto {

    @Parameter(description = "Number of page", example = "1")
    private Integer page;

    @Parameter(description = "The field by which we want to perform sorting. Available: state")
    private String orderBy;

    @Parameter(description = "Specify true if reverse sorting is needed")
    private Boolean desc;

    @Parameter(description = "The field by which we want to perform filter. Available: state, confectionerId, clientId")
    private String filterBy;

    @Parameter(description = "The state of field by which we want to perform filter.")
    private String filterValue;

    @Parameter(description = "The list of orders after filtering.")
    private String orders;
}

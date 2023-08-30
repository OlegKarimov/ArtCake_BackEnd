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
public class OrdersRequest {

    @Parameter(description = "Number of page", example = "1")
    private Integer page;
    @Parameter(description = "The field by which we want to perform sorting. Available: state")
    private String orderBy;
    @Parameter(description = "Specify true if reverse sorting is needed")
    private Boolean desc;
    private String filterBy;
    private String filterValue;
    private String orders;
}

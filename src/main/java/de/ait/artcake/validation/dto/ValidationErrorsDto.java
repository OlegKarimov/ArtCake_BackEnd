package de.ait.artcake.validation.dto;

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
@Schema(description = "Validation error")
public class ValidationErrorsDto {

    @Schema(description = "Errors list")
    private List<ValidationErrorDto> errors;
}

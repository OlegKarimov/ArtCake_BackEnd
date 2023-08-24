package de.ait.artcake.validation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Validation error")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ValidationErrorDto {

    @Schema(description = "The field where the error occurred", example = "email")
    private String field;

    @Schema(description = "Error message", example = "must be a well-formed email address")
    private String message;

    @Schema(description = "The value received from the client", example = "example.mail.de")
    private String rejectedValue;
}

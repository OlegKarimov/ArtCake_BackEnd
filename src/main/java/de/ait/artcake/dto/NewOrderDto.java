package de.ait.artcake.dto;


import de.ait.artcake.validation.constraints.CorrectDateValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrderDto {

    @Schema(description = "Quantity of cakes", example = "3")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Integer count;

    @CorrectDateValidator
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format YYYY-MM-DD and date must not be earlier than today.")
    @Schema(description = "Deadline date", example = "2023-10-10")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String deadline;

    @Schema(description = "В зависимости от ваших пожеланий общая стоимость может меняться, итоговую цену вы уточните с менеджером. Client wishes", example = "Make in blue and white colours")
    private String clientWishes;

}

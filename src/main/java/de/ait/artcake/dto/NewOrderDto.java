package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrderDto {


    @Schema(description = "Quantity of cakes", example = "3")
    private Double count;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format YYYY-MM-DD and date must not be earlier than today.")
    @Schema(description = "Deadline date ", example = "2023-02-02")
    private LocalDate deadline;

    @Schema(description = "Cake id", example = "1")
    private Integer cakeId;

    @Schema(description = "Client wishes", example = "Make in blue and white colours")
    private String description;

}

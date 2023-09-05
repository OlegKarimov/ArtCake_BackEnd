package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDto {

    @NotBlank
    @Schema(description = "New town of user", example = "Berlin")
    private String town;

    @NotBlank
    @Schema(description = "New zip code of user", example = "22112")
    private String zipCode;

    @NotBlank
    @Schema(description = "New street of user", example = "Tondorfer str.")
    private String street;

    @NotNull
    @Schema(description = "New house number of user", example = "10")
    private Integer houseNumber;

    @NotBlank
    @Schema(description = "New phone number of user", example = "+4917622334455")
    private String phoneNumber;

    @NotBlank
    @Schema(description = "New state of user", example = "NOT_CONFIRMED")
    private String state;

    @NotBlank
    @Schema(description = "New role of user", example = "CLIENT")
    private String role;
}

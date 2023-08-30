package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class RegisterDto {

    @Schema(description = "User First Name", example = "Will")
    @NotNull
    @NotBlank
    private String firstName;

    @Schema(description = "User Last Name", example = "Smith")
    @NotNull
    @NotBlank
    private String lastName;

    @Schema(description = "User email", example = "example@mail.com")
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Schema(description = "User Password", example = "Qwerty123!")
    @NotBlank
    @Size(min = 7, max = 30)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Week password. The password must be at least 7 characters long and no more than 30, " +
                    "containing both uppercase and lowercase letters," +
                    "digits, and at least 1 special character such as ! or ? etc.")
    private String password;

    @Schema(description = "User Town", example = "Berlin")
    @NotNull
    @NotBlank
    private String town;

    @Schema(description = "User street", example = "Sonnenallee")
    @NotNull
    @NotBlank
    private String street;

    @Schema(description = "User houseNumber", example = "17")
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @NotEmpty
    private Integer houseNumber;

    @Schema(description = "User PhoneNumber", example = "+4917612930456")
    @NotNull
    @NotBlank
    private String phoneNumber;
}

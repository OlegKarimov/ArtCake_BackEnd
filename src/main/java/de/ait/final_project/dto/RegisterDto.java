package de.ait.final_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class RegisterDto {

    @Schema(description = "User Name", example = "Will Smith")
    private String fullName;

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
    private String town;

    @Schema(description = "User street", example = "Sonnenallee")
    private String street;

    @Schema(description = "User houseNumber", example = "17")
    private Integer houseNumber;

    @Schema(description = "User PhoneNumber", example = "+4917612930456")
    private String phoneNumber;
}

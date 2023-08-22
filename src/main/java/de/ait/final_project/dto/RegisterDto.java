package de.ait.final_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @Schema(description = "User Name", example = "Will Smith")
    private String fullName;

    @Schema(description = "User email", example = "example@mail.com")
    private String email;

    @Schema(description = "User Password", example = "Qwerty123!")
    @NotBlank
    @Size(min = 7, max = 30)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Week password. The password must be at least 7 characters long and no more than 30, " +
                    "containing both uppercase and lowercase letters," +
                    "digits, and at least 1 special character such as ! or ? etc.")
    private String password;

    @Schema(description = "User Address", example = "Town - Berlin, Street - Sonnenallee, House Number - 17")
    private String address;

    @Schema(description = "User PhoneNumber", example = "+4917612930456")
    private String phoneNumber;
}

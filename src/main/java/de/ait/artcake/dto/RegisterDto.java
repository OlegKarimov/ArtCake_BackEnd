package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class RegisterDto {

    @Schema(description = "User First Name", example = "Will")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(description = "User Last Name", example = "Smith")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Schema(description = "User email", example = "example@mail.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(description = "User Password", example = "Qwerty123!")
    @NotBlank(message = "Password is required")
    @Size(min = 7, max = 30, message = "Password length must be between 7 and 30 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{7,}$",
            message = "Weak password. The password must contain at least one uppercase letter, " +
                    "one lowercase letter, one digit, and one special character.")
    private String password;

    @Schema(description = "User Town", example = "Berlin")
    @NotBlank(message = "Town is required")
    private String town;

    @Schema(description = "zipCode", example = "22331")
    @NotBlank(message = "Zip code is required")
    private String zipCode;

    @Schema(description = "User street", example = "Sonnenallee")
    @NotBlank(message = "Street is required")
    private String street;

    @Schema(description = "User houseNumber", example = "17")
    @NotNull(message = "House number is required")
    private Integer houseNumber;

    @Schema(description = "User PhoneNumber", example = "+4917612930456")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}

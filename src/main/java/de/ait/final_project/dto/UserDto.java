package de.ait.final_project.dto;

import de.ait.final_project.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Schema(description = "User Identifier",example = "1")
    private Long id;

    @Schema(description = "User Name", example = "Will Smith")
    private String fullName;

    @Schema(description = "User Email", example = "example@mail.com")
    private String email;

    @Schema(description = "User Address", example = "Town - Berlin, Street - Sonnenallee, House Number - 17")
    private String address;

    @Schema(description = "User PhoneNumber", example = "+4917611223344")
    private String phoneNumber;

    @Schema(description = "User Role: MANAGER, CLIENT, CONFECTIONER", example = "CLIENT")
    private String role;

    @Schema(description = "User Status: NOT_CONFIRMED, CONFIRMED ", example = "CONFIRMED")
    private String state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .state(user.getState().name())
                .role(user.getRole().name())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}

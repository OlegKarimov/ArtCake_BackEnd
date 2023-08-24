package de.ait.artcake.dto;

import de.ait.artcake.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 22-08-23/0022
 * ArtCake_BackEnd
 *
 * @author Dmytro Sainozhenko (AIT TR)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация о пользователе в рейтинге продуктов")
public class UserInCakeDto {
    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Email пользователя", example = "user@mail.com")
    private String email;

    public static UserInCakeDto from(User cake) {
        return UserInCakeDto.builder()
                .id(cake.getId())
                .build();
    }
}

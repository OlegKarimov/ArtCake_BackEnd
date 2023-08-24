package de.ait.artcake.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 22-08-23/0022
 * ArtCake_BackEnd
 *
 * @author Dmytro Sainozhenko (AIT TR)
 */
@Data
@Schema(description = "Adding a cake")
public class NewCakeDto {
    @Schema(description = "Ингридиенты", example = "Рецепт включает в себя...")
    private String text;

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long aboutUserId;

    @Schema(description = "Дата публикации в формате YYYY-MM-DD", example = "2022-02-02")
    private String publishDate;
}

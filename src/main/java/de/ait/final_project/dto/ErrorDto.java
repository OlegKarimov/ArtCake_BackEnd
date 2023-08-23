package de.ait.final_project.dto;

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
@Schema(description = "Информация об ошибке")
public class ErrorDto {
    @Schema(description = "Сообщение об ошибке", example = "Пользователь с указанным ID не найден")
    private String message;
}

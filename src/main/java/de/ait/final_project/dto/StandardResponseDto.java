package de.ait.final_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Message from the server. Errors and statuses.")
public class StandardResponseDto {

    @Schema(description = "Message Text")
    private String message;
}

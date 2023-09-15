package de.ait.artcake.handler;


import de.ait.artcake.dto.StandardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<StandardResponseDto> handleIncorrectUserIdException(RestException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(StandardResponseDto.builder()
                        .message(e.getMessage())
                        .build());
    }
}

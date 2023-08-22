package de.ait.final_project.handler;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException{
    private HttpStatus httpStatus;

    public RestException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus=httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

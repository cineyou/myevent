package adeo.leroymerlin.cdp.handler;

import adeo.leroymerlin.cdp.exception.EventNotFoundException;
import adeo.leroymerlin.cdp.exception.GlobalBusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EventNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("id-error-politic", e.getMessage(), request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NoResourceFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("id-error-politic", e.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e, HttpServletRequest request) {
        final String[] causes = e.getMessage().split(":");
        ErrorResponse errorResponse = new ErrorResponse("id-error-politic", causes[0], request.getRequestURI(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(GlobalBusinessException.class)
    public ResponseEntity<ErrorResponse> handle(GlobalBusinessException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("id-error-politic", e.getMessage(), request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("id-error-politic", e.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }




}

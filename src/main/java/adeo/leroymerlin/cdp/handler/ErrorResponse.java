package adeo.leroymerlin.cdp.handler;

import org.springframework.http.HttpStatus;


public record ErrorResponse(String id, String message, String path, HttpStatus status) {
}

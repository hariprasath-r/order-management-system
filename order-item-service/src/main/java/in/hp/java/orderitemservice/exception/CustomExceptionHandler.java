package in.hp.java.orderitemservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), ex.getMessage(), request.getDescription(false));
        log.error("Exception: handleAllException [{}]", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({OrderItemNotFoundException.class})
    public final ResponseEntity<Object> handleAllException(OrderItemNotFoundException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), ex.getMessage(), request.getDescription(false));
        log.error("Exception: ResourceNotFoundException [{}]", errorDTO);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler({OrderConflictException.class})
    public final ResponseEntity<Object> handleAllException(OrderConflictException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), ex.getMessage(), request.getDescription(false));
        log.error("Exception: ResourceConflictException [{}]", errorDTO);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), "Validation failed for input.", ex.getBindingResult().toString()
        );
        log.error("Exception: handleMethodArgumentNotValid [{}]", errorDTO);
        return new ResponseEntity<>(errorDTO, status);
    }
}

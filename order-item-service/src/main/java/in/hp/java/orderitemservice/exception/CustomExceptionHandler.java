package in.hp.java.orderitemservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@RestController
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({OrderItemException.class, Exception.class})
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), "Internal Error Occurred.", ex.getMessage());
        log.error("Exception: handleAllException [{}]", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                                           WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), "Input validation Failed.", ex.getMessage());
        log.error("Exception: ConstraintViolationException [{}]", errorDTO);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler({OrderItemNotFoundException.class})
    public final ResponseEntity<Object> handleOrderItemNotFoundException(OrderItemNotFoundException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), ex.getMessage(), request.getDescription(false));
        log.error("Exception: OrderItemNotFoundException [{}]", errorDTO);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler({OrderConflictException.class})
    public final ResponseEntity<Object> handleOrderConflictException(OrderConflictException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Date().toString(), ex.getMessage(), request.getDescription(false));
        log.error("Exception: OrderConflictException [{}]", errorDTO);
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

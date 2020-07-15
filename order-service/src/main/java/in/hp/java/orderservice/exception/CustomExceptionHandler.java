package in.hp.java.orderservice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.time.LocalDate;

@RestController
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        ErrorDTO errorDTO = new ErrorDTO(LocalDate.now().toString(), "Input validation Failed.", ex.getMessage());

        log.error("Exception: ConstraintViolationException [{}]", errorDTO);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public final ResponseEntity<Object> handleOrderItemNotFoundException(OrderNotFoundException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(LocalDate.now().toString(),
                ex.getMessage(), request.getDescription(false));

        log.error("Exception: OrderNotFoundException [{}]", errorDTO);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler({OrderConflictException.class})
    public final ResponseEntity<Object> handleOrderConflictException(OrderConflictException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(LocalDate.now().toString(), ex.getMessage(), request.getDescription(false));

        log.error("Exception: OrderConflictException [{}]", errorDTO);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }

    @ExceptionHandler({FeignProxyException.class})
    public final ResponseEntity<Object> handleHttpClientErrorException(FeignProxyException ex, WebRequest request) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(ex.getStatusCode());

        String responseBodyAsString = ex.getBody();
        ObjectMapper mapper = new ObjectMapper();

        ErrorDTO errorDTO;
        try {
            errorDTO = mapper.readValue(responseBodyAsString, ErrorDTO.class);

            log.error("Exception: handleHttpClientErrorException [{}]", errorDTO);
            return responseEntity.body(errorDTO);

        } catch (JsonProcessingException e) {
            errorDTO = new ErrorDTO(LocalDate.now().toString(), ex.getMessage(),
                    request.getDescription(false));

            log.error("Exception: handleHttpClientErrorException [{}]", errorDTO);
            return responseEntity.body(errorDTO);
        }
    }

    @ExceptionHandler({OrderException.class, Exception.class})
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(LocalDate.now().toString(), "Internal Error Occurred.", ex.getMessage());

        log.error("Exception: handleAllException [{}]", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDTO errorDTO = new ErrorDTO(LocalDate.now().toString(), "Validation failed for input.",
                ex.getBindingResult().toString());

        log.error("Exception: handleMethodArgumentNotValid [{}]", errorDTO);
        return new ResponseEntity<>(errorDTO, status);
    }
}

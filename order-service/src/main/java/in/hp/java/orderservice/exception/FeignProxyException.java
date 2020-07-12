package in.hp.java.orderservice.exception;

import org.springframework.http.HttpStatus;

public class FeignProxyException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorMessage;
    private final String body;

    public FeignProxyException(HttpStatus statusCode, String message, String body) {
        super(message);
        this.statusCode = statusCode;
        this.errorMessage = message;
        this.body = body;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FeignProxyException{");
        sb.append("statusCode=").append(statusCode);
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

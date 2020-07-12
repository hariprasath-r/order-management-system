package in.hp.java.orderservice.exception;

import org.springframework.http.HttpStatus;

public class FeignProxyException extends RuntimeException {

    private HttpStatus statusCode;
    private String errorMessage;
    private String body;

    public FeignProxyException(HttpStatus statusCode, String message, String body) {
        super(message);
        this.statusCode = statusCode;
        this.errorMessage = message;
        this.body = body;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

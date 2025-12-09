package br.com.example.proposal.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class ProposalExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseStatus annotationResponse = getResponseAnnotation(ex.getClass());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String status = httpStatus.name();
        int httpStatusCode = httpStatus.value();

        if(annotationResponse != null) {
            httpStatus = annotationResponse.value();
            if(annotationResponse.reason() == null || annotationResponse.reason().isEmpty())
                status = httpStatus.name();
            else
                status = annotationResponse.reason();
            httpStatusCode = annotationResponse.value().value();
        }else {
            log.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage(), httpStatusCode), httpStatus);
    }

    private ResponseStatus getResponseAnnotation(Class<?> exceptionClass) {
        return AnnotationUtils.findAnnotation(exceptionClass, ResponseStatus.class);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(
            org.springframework.dao.DataIntegrityViolationException ex) {

        String mensagem = "Registro já existente. Verifique os dados informados.";

        return new ResponseEntity<>(
                new ErrorResponse("BAD_REQUEST", mensagem, 400),
                HttpStatus.BAD_REQUEST
        );
    }

    @Getter
    @NoArgsConstructor
    public static class ErrorResponse {
        private String status;
        private String mensagem;
        private int httpStatusCode;

        public ErrorResponse(String status, String mensagem, int httpStatusCode) {
            this.status = status;
            this.mensagem = mensagem;
            this.httpStatusCode = httpStatusCode;
        }
    }
}

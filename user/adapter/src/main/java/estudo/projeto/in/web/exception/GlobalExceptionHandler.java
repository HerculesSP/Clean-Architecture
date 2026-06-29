package estudo.projeto.in.web.exception;

import estudo.projeto.exception.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ProblemDetail> applicationHandler(ApplicationException e){
        HttpStatus status = HttpStatus.valueOf(e.getCode());
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        pd.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(status).body(pd);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> domainHandler(DomainException e){
        HttpStatus status = HttpStatus.valueOf(e.getCode());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(status).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais campos da requisição estão inválidos."
        );
        pd.setProperty("timestamp", Instant.now());

        Map<String, String> errosDosCampos = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Campo inválido.",
                        (erroExistente, novoErro) -> erroExistente
                ));

        pd.setProperty("errors", errosDosCampos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolation(ConstraintViolationException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Erro de validação nos parâmetros da URL ou rota."
        );
        pd.setProperty("timestamp", Instant.now());

        Map<String, String> errosDasVariaveis = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                .reduce((primeiro, segundo) -> segundo)
                                .map(Object::toString)
                                .orElse(violation.getPropertyPath().toString()),
                        ConstraintViolation::getMessage,
                        (erroExistente, novoErro) -> erroExistente
                ));

        pd.setProperty("errors", errosDasVariaveis);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

}

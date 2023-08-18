package com.solucao.transacoes.infraestrutura.exceptionhandler;

import com.solucao.transacoes.TransacoesApplication;
import com.solucao.transacoes.dominio.businessexception.AccountDuplicatedBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger LOG = LoggerFactory
            .getLogger(TransacoesApplication.class);

    @ExceptionHandler(AccountDuplicatedBusinessException.class)
    public ResponseEntity<Object> accountDuplicatedBusinessException(
            AccountDuplicatedBusinessException ex, WebRequest request) {

        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        return getObjectResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> emptyResultDataAccessException(Exception ex, WebRequest request) {

        return getObjectResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());

    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> dataIntegrityViolationException(Exception ex, WebRequest request) {

        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {

        traceLogger(ex);

        return getObjectResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

    }

    private void traceLogger(Exception ex) {

        StackTraceElement[] trace = ex.getStackTrace();
        LOG.info("Start trace");
        for(int i = 0; i < trace.length; i++){
            LOG.error("AdviceExceptionTrace -> " + trace[i].toString());
        }
        LOG.info("End trace");
    }

    private ResponseEntity<Object> getObjectResponseEntity(HttpStatus internalServerError, String message) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", internalServerError.value());
        body.put("errors", Arrays.asList(message));

        return new ResponseEntity<>(body, internalServerError);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
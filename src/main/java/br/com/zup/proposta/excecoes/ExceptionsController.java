package br.com.zup.proposta.excecoes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request){

        List<FieldError> descricaoErros = ex.getBindingResult().getFieldErrors();
        List<ObjectError> descricaoErrosObject = ex.getBindingResult().getGlobalErrors();

        ErrorObject errorObject = new ErrorObject(LocalDateTime.now(), descricaoErros, descricaoErrosObject);
        return new ResponseEntity<>(errorObject, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}

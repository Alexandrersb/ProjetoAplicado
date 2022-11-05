package com.br.projetoaplicado.ExceptionHandler;

import com.br.projetoaplicado.Model.Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class UserControllerAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> userNotFound (UserNotFoundException e ){
        MessageExceptionHandler erro = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Usuário não foi encontrado"
        );
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(Dataintegrityviolationexception.class)
    public ResponseEntity<MessageExceptionHandler> dataintegrityviolationexception (Dataintegrityviolationexception e){
        MessageExceptionHandler erro = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), e.getMessage() );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }



    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid (MethodArgumentNotValidException argumentNotValid){

        BindingResult result = argumentNotValid.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder sb = new StringBuilder ("Os campos seguintes não podem ser nulos ou passados em branco: ");
        for (FieldError fieldError : fieldErrors) {
            sb.append(" | ");
            sb.append(" -> ");
            sb.append(fieldError.getField());
            sb.append(" <- ");
        }

        MessageExceptionHandler erro = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString()
        );
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }


}

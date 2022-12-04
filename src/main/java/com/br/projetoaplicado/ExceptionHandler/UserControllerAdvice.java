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
import java.util.*;
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
                new Date(), HttpStatus.BAD_REQUEST.value(), "Já possui um usuário com esse email cadastrado." );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageExceptionHandler> constraintViolationException (ConstraintViolationException e){
        MessageExceptionHandler erro = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(),"Email não está no formato correto."
        );
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> argumentsNotValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseBody
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<MessageExceptionHandler> responsestatusexception (ResponseStatusException e){
        MessageExceptionHandler erro = new MessageExceptionHandler(
                new Date(), HttpStatus.UNAUTHORIZED.value(),"Token utilizado não está correto"
        );
        return new ResponseEntity<>(erro, HttpStatus.UNAUTHORIZED);
    }



}

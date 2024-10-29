package com.edu.ifpb.caprin.apresentation.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.edu.ifpb.caprin.business.service.exception.CpfAlreadyExistsException;
import com.edu.ifpb.caprin.business.service.exception.EmailAlreadyExistsException;
import com.edu.ifpb.caprin.business.service.exception.NoSuchElementFoundException;
import com.edu.ifpb.caprin.business.service.exception.PasswordNotMatchingException;
import com.edu.ifpb.caprin.business.service.exception.TokenException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ManipuladorGlobalExcecoes {

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Resposta<Object> handleTokenException(
            TokenException exception,
            HttpServletRequest request) {
        return createResponse(request.getServletPath(), List.of(exception.getMessage()));
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Resposta<Object> handleNoSuchElementFoundException(
            NoSuchElementFoundException exception,
            HttpServletRequest request) {
        return createResponse(request.getServletPath(), List.of(exception.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Resposta<Object> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException exception,
            HttpServletRequest request) {
        return createResponse(request.getServletPath(), List.of(exception.getMessage()));
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Resposta<Object> handleCpfAlreadyExistsException(
            CpfAlreadyExistsException exception,
            HttpServletRequest request) {
        return createResponse(request.getServletPath(), List.of(exception.getMessage()));
    }

    @ExceptionHandler(PasswordNotMatchingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Resposta<Object> handlePasswordNotMatchingException(
            PasswordNotMatchingException exception,
            HttpServletRequest request) {
        return createResponse(request.getServletPath(), List.of(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Resposta<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String endpoint = request.getServletPath();
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return createResponse(endpoint, errors);
    }

    private Resposta<Object> createResponse(String endpoint, List<String> errors) {
        return Resposta.builder()
                .endpoint(endpoint)
                .erros(errors)
                .build();
    }

}
package com.farmalucia.FarmaLucia.infra.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class TratadorExceptions {

    @ExceptionHandler(NoSuchElementException.class)
    public String trataErro404(){
        return "erro/404";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String trataErro500(){
        return "erro/500";
    }
}

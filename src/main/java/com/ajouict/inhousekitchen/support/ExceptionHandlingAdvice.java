package com.ajouict.inhousekitchen.support;

import com.ajouict.inhousekitchen.exception.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlingAdvice.class);

    @ExceptionHandler(UnAuthorizedException.class)
    public String unAuthorized(){
        log.debug("UnAuthorizedException is happened");
        return "return:/users/loginForm";
    }
}

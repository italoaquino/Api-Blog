package com.blog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExcpetionHandler {

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFound e , HttpServletRequest httpServletRequest){
            StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

}

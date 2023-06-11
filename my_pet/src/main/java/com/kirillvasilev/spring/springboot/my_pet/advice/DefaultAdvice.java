package com.kirillvasilev.spring.springboot.my_pet.advice;

import com.kirillvasilev.spring.springboot.my_pet.exception.IdNotFoundException;
import com.kirillvasilev.spring.springboot.my_pet.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(IdNotFoundException.class)
        public ResponseEntity<ExceptionResponse> handleException(IdNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
}

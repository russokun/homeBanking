package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.utils.MissingParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MissingParameterException.class)
  public ResponseEntity<String> handleMissingParameterException(MissingParameterException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    return new ResponseEntity<>("Invalid or missing parameters", HttpStatus.FORBIDDEN);
  }

  // Otros manejadores de excepciones...
}

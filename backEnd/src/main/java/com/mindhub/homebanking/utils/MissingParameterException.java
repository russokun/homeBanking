package com.mindhub.homebanking.utils;

public class MissingParameterException extends RuntimeException {
  public MissingParameterException(String message) {
    super(message);
  }
}

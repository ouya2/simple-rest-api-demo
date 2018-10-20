package com.acme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDataFormatException extends RuntimeException {

  public InvalidDataFormatException() {
    super();
  }

  public InvalidDataFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidDataFormatException(String message) {
    super(message);
  }

  public InvalidDataFormatException(Throwable cause) {
    super(cause);
  }
}

package com.acme.exception;

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

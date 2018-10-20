package com.acme.model;

public class ErrorDetails {

  private final String detail;
  private final String message;

  public ErrorDetails(Exception ex, String detail) {
    this.message = ex.getLocalizedMessage();
    this.detail = detail;
  }
}

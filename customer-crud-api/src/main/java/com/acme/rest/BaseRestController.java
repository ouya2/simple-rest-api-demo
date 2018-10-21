package com.acme.rest;

import com.acme.exception.ResourceNotFoundException;
import com.acme.model.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class BaseRestController {

  protected final Logger LOG = LoggerFactory.getLogger(getClass());

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException.class)
  public
  @ResponseBody
  ErrorDetails handleResourceNotFoundException(ResourceNotFoundException ex) {
    LOG.info("ResourceNotFoundException handler:" + ex.getMessage());
    return new ErrorDetails(ex, "Resource cannot be located.");
  }

  public <T> T checkResourceFound(final T resource) {
    if (resource == null) {
      throw new ResourceNotFoundException("resource not found");
    }
    return resource;
  }
}

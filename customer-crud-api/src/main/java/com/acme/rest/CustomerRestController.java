package com.acme.rest;

import com.acme.model.Customer;
import com.acme.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/customers")
@Api(value = "CustomerControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController extends BaseRestController {

  @Autowired
  private CustomerService customerService;

  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create a customer resource.")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "New Customer created"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "System error encountered")})
  public Customer createCustomer(@RequestBody Customer customer) {
    return this.customerService.createCustomer(customer);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get a customer resource.")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Customer resource cannot be found.")})
  public Customer getCustomer(@ApiParam(value = "The ID of the customer.", required = true)
      @PathVariable("id") Long id) {
    Customer customer = customerService.getCustomer(id);
    checkResourceFound(customer);
    return customer;
  }




}

package com.acme.rest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acme.exception.ResourceNotFoundException;
import com.acme.model.Customer;
import com.acme.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRestControllerTest {

  @Mock
  private CustomerService customerService;

  CustomerRestController controller;

  @Before
  public void setup() {
    controller = new CustomerRestController(customerService);
  }

  @Test
  public void testCreateCustomer() {
    Customer testCustomer = new Customer();
    when(customerService.createCustomer(testCustomer)).thenReturn(testCustomer);

    controller.createCustomer(testCustomer);
    verify(customerService, times(1)).createCustomer(testCustomer);
  }

  @Test
  public void testGetCustomer() {
    when(customerService.getCustomer(123L)).thenReturn(new Customer());

    controller.getCustomer(123L);

    verify(customerService, times(1)).getCustomer(123L);
  }

  @Test
  public void testDeleteCustomer() {
    when(customerService.getCustomer(123L)).thenReturn(new Customer());

    controller.deleteCustomer(123L);

    verify(customerService, times(1)).deleteCustomer(123L);
  }

  @Test
  public void testUpdateCustomer() {
    Customer testCustomer = new Customer();
    when(customerService.getCustomer(123L)).thenReturn(new Customer());

    controller.updateCustomer(123L, testCustomer);

    verify(customerService, times(1)).updateCustomer(any(Customer.class));
  }

  @Test (expected = ResourceNotFoundException.class)
  public void testDeleteCustomer_resourceNotAvailable() {
    when(customerService.getCustomer(123L)).thenReturn(null);

    controller.deleteCustomer(123L);
  }

  @Test (expected = ResourceNotFoundException.class)
  public void testUpdateCustomer_resourceNotAvailable() {
    when(customerService.getCustomer(123L)).thenReturn(null);

    controller.updateCustomer(123L, new Customer());
  }

  @Test (expected = ResourceNotFoundException.class)
  public void testGetCustomer__resourceNotAvailable() {
    when(customerService.getCustomer(123L)).thenReturn(null);

    controller.getCustomer(123L);
  }
}

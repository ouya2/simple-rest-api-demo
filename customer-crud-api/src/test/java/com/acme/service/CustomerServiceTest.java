package com.acme.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.acme.model.Customer;
import com.acme.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @Mock
  private CustomerRepository repo;

  CustomerService service;

  @Before
  public void setup() {
    service = new CustomerService(repo);
  }

  @Test
  public void testCreateCustomer() {
    Customer customer = new Customer("Dick", "Smith");
    when(repo.save(customer)).thenReturn(new Customer(123L, "Dick", "Smith"));

    Customer result = service.createCustomer(customer);
    verify(repo, times(1)).save(customer);

    assertTrue(123L == result.getId());
    assertEquals("Dick", result.getFirstName());
    assertEquals("Smith", result.getLastName());
  }

  @Test
  public void testGetCustomer() {
    when(repo.findOne(123L)).thenReturn(new Customer(123L, "Dick", "Smith"));

    Customer result = service.getCustomer(123L);
    verify(repo, times(1)).findOne(123L);

    assertTrue(123L == result.getId());
    assertEquals("Dick", result.getFirstName());
    assertEquals("Smith", result.getLastName());
  }

  @Test
  public void testDeleteCustomer() {
    service.deleteCustomer(123L);
    verify(repo, times(1)).delete(123L);
  }

  @Test
  public void testUpdateCustomer() {
    Customer customer = new Customer("Dick", "Smith");
    when(repo.save(customer)).thenReturn(new Customer(123L, "Dick", "Smith"));

    Customer result = service.updateCustomer(customer);
    verify(repo, times(1)).save(customer);

    assertTrue(123L == result.getId());
    assertEquals("Dick", result.getFirstName());
    assertEquals("Smith", result.getLastName());
  }
}

package com.acme.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.acme.model.Customer.Gender;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class CustomerTest {

  @Test
  public void testCustomer() {
    Customer newCustomer = getCustomer();
    assertEquals("John", newCustomer.getFirstName());
    assertEquals("Lennings", newCustomer.getLastName());
    assertEquals("Walli", newCustomer.getMiddleName());
    assertEquals("Mr", newCustomer.getTitle());
    assertEquals("Single", newCustomer.getMaritalStatus());
    assertEquals(false, newCustomer.getNabCustomer());
    assertEquals(Integer.valueOf(25), newCustomer.getCreditRating());
    assertEquals(Gender.M, newCustomer.getSex());
    assertEquals("JL", newCustomer.getInitials());
    assertEquals(1, newCustomer.getAddresses().size());

    newCustomer = new Customer(123L, "someone", "somebody");
    assertEquals("someone", newCustomer.getFirstName());
    assertEquals("somebody", newCustomer.getLastName());
    assertEquals(Long.valueOf(123L), newCustomer.getId());
  }

  @Test
  public void testToString() {
    Customer newCustomer = getCustomer();
    assertFalse(newCustomer.toString().isEmpty());
  }

  private Customer getCustomer() {
    Address address = new Address("15", "Shortland", "Richmond", "Melbourne");
    Set<Address> addresses = new HashSet<>();
    addresses.add(address);

    Customer newCustomer = new Customer("John", "Lennings");
    newCustomer.setMiddleName("Walli");
    newCustomer.setTitle("Mr");
    newCustomer.setNabCustomer(false);
    newCustomer.setCreditRating(25);
    newCustomer.setSex(Gender.M);
    newCustomer.setMaritalStatus("Single");
    newCustomer.setAddresses(addresses);
    newCustomer.setInitials("JL");
    return newCustomer;
  }
}

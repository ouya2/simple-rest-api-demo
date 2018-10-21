package com.acme.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.acme.model.Address;
import com.acme.model.Customer;
import com.acme.model.Customer.Gender;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class BeanUtilsTest {

  @Test
  public void copyCustomerProperties() {
    Customer sourceCustomer = new Customer("John", "Smith");
    sourceCustomer.setCreditRating(10);
    sourceCustomer.setNabCustomer(true);
    sourceCustomer.setMaritalStatus("Married");
    sourceCustomer.setTitle("Dr");
    Address sourceAddress = new Address("12", "Crodale", "Malvern", "Melbourne");
    sourceAddress.setId(347L);
    Set<Address> sourceAddresses = new HashSet<>();
    sourceAddresses.add(sourceAddress);
    sourceCustomer.setAddresses(sourceAddresses);

    Customer targetCustomer = new Customer("Lewis", "Smith");
    targetCustomer.setCreditRating(13);
    targetCustomer.setNabCustomer(false);
    targetCustomer.setMiddleName("Timothy");
    targetCustomer.setInitials("Mr");
    targetCustomer.setMaritalStatus("Single");
    targetCustomer.setSex(Gender.M);
    targetCustomer.setTitle("Mr");
    Address targetAddress = new Address("5", "Goode", "Collingwood", "Sydney");
    targetAddress.setId(347L);
    Set<Address> targetAddresses = new HashSet<>();
    targetAddresses.add(targetAddress);
    targetCustomer.setAddresses(targetAddresses);

    BeanUtils.copyCustomerProperties(sourceCustomer, targetCustomer);

    // Verification
    assertEquals("John", targetCustomer.getFirstName());
    assertEquals("Smith", targetCustomer.getLastName());
    assertEquals("Married", targetCustomer.getMaritalStatus());
    assertEquals("Dr", targetCustomer.getTitle());
    assertEquals(true, targetCustomer.getNabCustomer());
    assertEquals(Integer.valueOf(10), targetCustomer.getCreditRating());

    targetAddress = targetCustomer.getAddresses().stream().filter(addr -> addr.getId() == 347L).findFirst().get();
    assertEquals("12", targetAddress.getAddressNumber());
    assertEquals("Crodale", targetAddress.getStreet());
    assertEquals("Malvern", targetAddress.getSuburb());
    assertEquals("Melbourne", targetAddress.getCity());
  }

  @Test
  public void getIgnoreCustomerPropertyNames() {
    Customer customer = new Customer("John", "Smith");
    String[] allFields = BeanUtils.getIgnoreCustomerPropertyNames(customer);
    assertEquals("id", allFields[0]);
    assertEquals("addresses", allFields[1]);
    assertTrue(allFields.length == 9);
    for (String field : allFields) {
      assertFalse("firstName".equalsIgnoreCase(field));
      assertFalse("lastName".equalsIgnoreCase(field));
    }
  }

  @Test (expected = RuntimeException.class)
  public void getIgnoreCustomerPropertyNames_invalidCustomer() {
    Customer customer = null;
    BeanUtils.getIgnoreCustomerPropertyNames(customer);
  }

  @Test (expected = RuntimeException.class)
  public void copyAddresses_invalidTarget(){
    Set<Address> addresses = new HashSet<>();
    addresses.add(new Address("2/10", "Boulevard", "Avenue", "2345"));
    BeanUtils.copyAddresses(addresses, Collections.EMPTY_SET);
  }

  @Test
  public void copyAddresses() {
    Address address = new Address("2/10", "Boulevard", "Avenue", "Sydney");
    address.setId(123L);
    Set<Address> sourceAddresses = new HashSet<>();
    sourceAddresses.add(address);
    Set<Address> targetAddresses = new HashSet<>();
    Address targetAddress = new Address();
    targetAddress.setId(123L);
    targetAddresses.add(targetAddress);
    BeanUtils.copyAddresses(sourceAddresses, targetAddresses);
    targetAddress = targetAddresses.stream().filter(addr -> addr.getId() == 123L).findFirst().get();
    assertEquals("2/10", targetAddress.getAddressNumber());
    assertEquals("Boulevard", targetAddress.getStreet());
    assertEquals("Avenue", targetAddress.getSuburb());
    assertEquals("Sydney", targetAddress.getCity());
  }

  @Test (expected = RuntimeException.class)
  public void copyAddress_sourceNotInTarget() {
    Address address = new Address("2/10", "Boulevard", "Avenue", "2345");
    address.setId(234L);
    Set<Address> sourceAddresses = new HashSet<>();
    sourceAddresses.add(address);
    Set<Address> targetAddresses = new HashSet<>();
    Address targetAddress = new Address();
    targetAddress.setId(123L);
    targetAddresses.add(targetAddress);
    BeanUtils.copyAddresses(sourceAddresses, targetAddresses);
  }

  @Test (expected = RuntimeException.class)
  public void copyAddress_sourceNullId() {
    Address address = new Address("2/10", "Boulevard", "Avenue", "2345");
    Set<Address> sourceAddresses = new HashSet<>();
    sourceAddresses.add(address);
    Set<Address> targetAddresses = new HashSet<>();
    Address targetAddress = new Address();
    targetAddress.setId(123L);
    targetAddresses.add(targetAddress);
    BeanUtils.copyAddresses(sourceAddresses, targetAddresses);
  }
}

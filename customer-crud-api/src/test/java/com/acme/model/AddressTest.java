package com.acme.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddressTest {

  @Test
  public void testAddress() {
    Address sourceAddress = new Address("12", "Crodale", "Malvern", "Melbourne");
    sourceAddress.setId(347L);
    sourceAddress.setCountry("Australia");
    sourceAddress.setPinCode("3156");
    assertEquals("12", sourceAddress.getAddressNumber());
    assertEquals("Crodale", sourceAddress.getStreet());
    assertEquals("Malvern", sourceAddress.getSuburb());
    assertEquals("Melbourne", sourceAddress.getCity());
    assertEquals("Australia", sourceAddress.getCountry());
    assertEquals("3156", sourceAddress.getPinCode());
  }

  @Test
  public void testEquals() {
    Address address1 = new Address("12", "Crodale", "Malvern", "Melbourne");
    Address address2 = new Address("12", "Crodale", "Somewhere", "Sydney");
    assertFalse(address1.equals(address2));
    assertFalse(address1.hashCode() == address2.hashCode());
    assertEquals(address1, address1);
    assertTrue(address1.hashCode() == address1.hashCode());
  }

}

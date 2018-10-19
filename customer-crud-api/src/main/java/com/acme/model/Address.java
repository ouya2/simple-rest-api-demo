package com.acme.model;

import com.google.common.base.MoreObjects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

  @Id
  @GeneratedValue
  @Column(name = "address_id")
  private Long id;

  @Column(name = "address_number")
  private String addressNumber;

  @NotNull
  @Column(name = "street_name")
  private String street;

  @NotNull
  @Column
  private String suburb;

  @NotNull
  @Column
  private String city;

  @NotNull
  @Column
  private String state;

  @Column
  private String country;

  @NotNull
  @Column(name = "post_code")
  private String pinCode;

  public Address() {
  }

  public Address(String addressNumber, String city, String state, String pinCode) {
    this.addressNumber = addressNumber;
    this.city = city;
    this.state = state;
    this.pinCode = pinCode;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddressNumber() {
    return addressNumber;
  }

  public void setAddressNumber(String addressNumber) {
    this.addressNumber = addressNumber;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPinCode() {
    return pinCode;
  }

  public void setPinCode(String pinCode) {
    this.pinCode = pinCode;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("Street No.", addressNumber)
        .add("Street Name", street)
        .add("Suburb", suburb)
        .add("City", city)
        .add("State", state)
        .add("Country", country)
        .add("Pin code", pinCode)
        .toString();
  }
}

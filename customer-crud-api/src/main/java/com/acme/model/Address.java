package com.acme.model;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Address implements Serializable {

  public static final String ID_PROPERTY   = "id";

  @ApiModelProperty(required = true, position = 1)
  @Id
  @GeneratedValue
  @Column(name = "address_id")
  private Long id;

  @Column(name = "address_number")
  private String addressNumber;

  @ApiModelProperty(required = true)
  @NotNull
  @Column(name = "street_name")
  private String street;

  @ApiModelProperty(required = true)
  @NotNull
  @Column
  private String suburb;

  @ApiModelProperty(required = true)
  @NotNull
  @Column
  private String city;

  @Column
  private String state;

  @Pattern(regexp = "^[A-Za-z|\\s]+$")
  @Column
  private String country;

  @Pattern(regexp = "^[0-9]+$")
  @Column(name = "post_code")
  private String pinCode;

  public Address() {
  }

  public Address(String addressNumber, String street, String suburb, String city) {
    this.addressNumber = addressNumber;
    this.street = street;
    this.suburb = suburb;
    this.city = city;
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

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Address other = (Address) obj;
    return Objects.equals(this.addressNumber, other.addressNumber)
        && Objects.equals(this.street, other.street)
        && Objects.equals(this.suburb, other.suburb)
        && Objects.equals(this.city, other.city)
        && Objects.equals(this.state, other.state)
        && Objects.equals(this.pinCode, other.pinCode)
        && Objects.equals(this.country, other.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        this.addressNumber, this.street, this.suburb,
        this.city, this.state, this.pinCode, this.country);
  }
}

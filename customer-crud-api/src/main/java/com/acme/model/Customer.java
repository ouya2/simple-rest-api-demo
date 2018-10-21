package com.acme.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

  public static final String ID_PROPERTY              = "id";
  public static final String ADDRESSES_PROPERTY       = "addresses";

  @ApiModelProperty(required = true)
  @Id
  @GeneratedValue
  @Column(name = "customer_id")
  private Long id;

  @ApiModelProperty(required = true)
  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @ApiModelProperty(required = true)
  @NotNull
  @Column(name = "last_name")
  private String lastName;

  @Column(name = "middle_name")
  private String middleName;

  @Column
  private String initials;

  @Column
  private String title;

  @Column(name = "gender")
  private Gender sex;

  @Column(name = "marital_status")
  private String maritalStatus;

  @Max(100)
  @Min(0)
  @Column(name = "credit_rating")
  private Integer creditRating;

  @Column(name = "is_nab_customer")
  private Boolean isNabCustomer;

  @Valid
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  private Set<Address> addresses;

  public Customer() {
  }

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(Long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Gender getSex() {
    return sex;
  }

  public void setSex(Gender sex) {
    this.sex = sex;
  }

  public String getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(String maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public Integer getCreditRating() {
    return creditRating;
  }

  public void setCreditRating(Integer creditRating) {
    this.creditRating = creditRating;
  }

  public Boolean getNabCustomer() {
    return isNabCustomer;
  }

  public void setNabCustomer(Boolean nabCustomer) {
    isNabCustomer = nabCustomer;
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("First Name", firstName)
        .add("Middle Name", middleName)
        .add("Surname", lastName)
        .add("Initials", initials)
        .add("Title", title)
        .add("Sex", sex)
        .add("Marital Status", maritalStatus)
        .add("Credit Rating", creditRating)
        .add("Is NAB Customer", isNabCustomer)
        .add("address", addresses)
        .toString();
  }

  public enum Gender {
    M,

    F
  }
}

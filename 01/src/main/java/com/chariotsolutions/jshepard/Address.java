package com.chariotsolutions.jshepard;

public class Address {
  private final String street;
  private final City city;

  public Address(String street, City city) {
    this.street = street;
    this.city = city;
  }

  public City getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }
}

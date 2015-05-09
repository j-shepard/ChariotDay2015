package com.chariotsoluttions.jshepard;

import java.util.Optional;

public class Address {
  private final String street;
  private final Optional<City> city;

  public Address(String street, City city) {
    this.street = street;
    this.city = Optional.ofNullable(city);
  }

  public Optional<City> getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }
}

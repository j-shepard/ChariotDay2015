package com.chariotsoluttions.jshepard;

import java.util.Optional;

public class Person {
  private final String name;
  private final Optional<Address> address;

  public Person(String name, Address address) {
    this.name = name;
    this.address = Optional.ofNullable(address);
  }

  public Person(String name) {
    this.name = name;
    this.address = Optional.empty();
  }

  public String getName() {
    return name;
  }

  public Optional<Address> getAddress() {
    return address;
  }
}

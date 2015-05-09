package com.chariotsolutions.jshepard;

public class Person {
  private final String name;
  private final Address address;

  public Person(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  public Person(String name) {
    this.name = name;
    this.address = null;
  }

  public String getName() {
    return name;
  }

  public Address getAddress() {
    return address;
  }
}

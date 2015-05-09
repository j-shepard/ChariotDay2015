package com.chariotsoluttions.jshepard;

import java.util.Optional;

public class Main {
  public static final void main(String... args) {
    Optional<Person> person = Optional.of(new Person("name"));
    System.out.println("City name:" + person.flatMap(Person::getAddress).flatMap(Address::getCity).map(City::getName).orElse(null));
  }
}

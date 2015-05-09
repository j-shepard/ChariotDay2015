package com.chariotsolutions.jshepard;

public class Main {
  public static final void main(String... args) {
    Person person = new Person("name");
    System.out.println("City name:" + person.getAddress().getCity().getName());
  }
}

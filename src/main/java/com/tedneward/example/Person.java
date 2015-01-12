package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private static int personCount = 0;
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    this.personCount ++;
    name = n;
    age = a;
    salary = s;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
      if(age < 0 ) {
          throw new IllegalArgumentException();
      }
      this.age = age;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
      if(name == null) {
          throw new IllegalArgumentException();
      }
      this.name = name;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) { this.salary = salary; }

  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  public int count() {
      return this.personCount;
  }

  public static ArrayList<Person> getNewardFamily() {
      Person ted = new Person("Ted", 41, 250000.00);
      Person charlotte = new Person("Charlotte", 43, 150000.00);
      Person michael = new Person("Michael", 22, 10000.00);
      Person matthew = new Person("Matthew", 15, 0.00);

      ArrayList<Person> family = new ArrayList<Person>();
      family.add(ted);
      family.add(charlotte);
      family.add(michael);
      family.add(matthew);

      return family;
  }
  
  public boolean equals(Person other) {
    Person p = other;
    return (this.name.equals(p.name) && this.age == p.age);
  }

  // sort by salary desc
  public int compareTo(Person comparePerson) {
      return (int) comparePerson.getSalary() - (int) this.getSalary();
  }


   public static class AgeComparator implements Comparator<Person> {
      public int compare(Person p1, Person p2) {
          if(p2.getAge() > p1.getAge()) {
              return -1;
          } else if(p2.getAge() == p1.getAge()) {
              return 0;
          } else {
              return 1;
          }
      }
  };
  public String toString() {
      //[Person name:Fird Birfle age:20 salary:195750.22]
      return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static void main(String[] args) {
//      Person p = new Person();
//      p.setAge(20);
//      p.setName("Fird Birfle");
//      p.setSalary(195750.22);
//      System.out.println(p.toString());

//      Person p1 = new Person("Ted", 43, 250000);
//      Person p2 = p1;
//      System.out.println("true: " + p1.equals(p2));
//
//      Person p3 = new Person("Ted", 43, 250000);
//      System.out.println("true: " + p1.equals(p3));
////
//      Person p4 = new Person("Ted", 43, 500000);
//      System.out.println("true: " + p1.equals(p4));
////
//      Person p5 = new Person("Ted", 45, 250000);
//      System.out.println("false: " + p1.equals(p5));
////
//      Person p6 = new Person();
//      System.out.println("false: " + p1.equals(p6));
////
//      System.out.println("false: " + p1.equals(false));
//      System.out.println("false: " + p1.equals(new Integer(27)));'

      List<Person> people = Person.getNewardFamily();
      Collections.sort(people, new Person.AgeComparator());

      // should be matthew michael, ted, charlotte
      for(Person person : people) {
          System.out.println(person.toString());
      }
  }
}

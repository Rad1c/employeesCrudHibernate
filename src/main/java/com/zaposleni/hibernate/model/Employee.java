package com.zaposleni.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zaposleni")
public class Employee {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "ime")
  private String name;

  @Column(name = "broj_godina")
  private int age;

  @Column(name = "visina_dohotka")
  private double amount;

  @Column(name = "adresa")
  private String address;
  
  public Employee(){
      super();
  }
  
  public Employee(int id, String name, int age, String address, Double amount ){
      this.id = id;
      this.name = name;
      this.age = age;
      this.amount = amount;
      this.address = address;
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}




package com.mindhub.homebanking.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  String number;
  LocalDate creationDate;
  double balance;

  public Account() {
  }

  public Account(String number, LocalDate creationDate, double balance) {
    this.number = number;
    this.creationDate = creationDate;
    this.balance = balance;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void setId(Long id) {
    this.id = id;
  }
  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Account{" +
            "id=" + id +
            ", number='" + number + '\'' +
            ", creationDate=" + creationDate +
            ", balance=" + balance +
            '}';
  }

}
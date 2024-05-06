package com.mindhub.homebanking.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
  @jakarta.persistence.Id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName, lastName, email;
  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<Account> accounts = new HashSet<>();

  public Client() {
  }

  public Client(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public long getId() {
    return id;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Account> getAccounts() {
    return accounts;
  }

  public void addAccount(Account account) {
    account.setClient(this);
    accounts.add(account);
  }

  @Override
  public String toString() {
    return "Client{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", accounts='" +accounts  + '\'' +
            '}';
  }
}



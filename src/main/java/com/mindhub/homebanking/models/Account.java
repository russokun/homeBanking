package com.mindhub.homebanking.models;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String number;
  private LocalDate creationDate;
  private Double balance;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="clientId")
  private Client client;

  @OneToMany(mappedBy = "clientAccount", fetch = FetchType.EAGER)
  private Set<Transaction> transactions = new HashSet<>();
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

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Set<Transaction> getTransactions() {
    return transactions;
  }
  public void addTransaction (Transaction transaction){
    transaction.setClientAccount(this);
    transactions.add(transaction);
  }

  @Override
  public String toString() {
    return "Account{" +
            "id=" + id +
            ", number='" + number + '\'' +
            ", creationDate=" + creationDate +
            ", balance=" + balance +
            ", transactions=" + transactions +
            '}';
  }

}
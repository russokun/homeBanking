package com.mindhub.homebanking.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private Double amount;
  private LocalDateTime date;
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="accountId")
  private Account clientAccount;

  public Transaction() {
  }

  public Transaction(String description, Double amount, LocalDateTime date, TransactionType type) {
    this.description = description;
    this.amount = amount;
    this.date = date;
    this.type = type;

  }

  public Transaction(String description, Double amount, LocalDateTime date, TransactionType type, Account destinationAccount) {
    this.description = description;
    this.amount = amount;
    this.date = date;
    this.type = type;
    this.clientAccount = destinationAccount;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public Account getClientAccount() {
    return clientAccount;
  }

  public void setClientAccount(Account clientAccount) {
    this.clientAccount = clientAccount;
  }

  @Override
  public String toString() {
    return "Transaction{" +
            "id=" + id +
            ", type=" + type +
            ", description='" + description + '\'' +
            ", amount=" + amount +
            ", date=" + date +
            '}';
  }
}
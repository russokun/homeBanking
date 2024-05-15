package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import java.time.LocalDateTime;

public class TransactionDto {
  private Long id;
  private String description;
  private Double amount;
  private LocalDateTime date;
  private TransactionType type;

  public TransactionDto(Transaction transaction) {
    this.id = transaction.getId();
    this.description = transaction.getDescription();
    this.amount = transaction.getAmount();
    this.date = transaction.getDate();
    this.type = transaction.getType();
  }

  public Double getAmount() {
    return amount;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public TransactionType getType() {
    return type;
  }
}

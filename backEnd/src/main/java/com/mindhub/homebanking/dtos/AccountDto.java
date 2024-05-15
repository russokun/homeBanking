package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;
import java.util.Set;

public class AccountDto {
  private Long id;
  private String number;
  private LocalDate creationDate;
  private Double balance;
  private Set<TransactionDto> transactions;

  public AccountDto(Account account) {
    this.id = account.getId();
    this.number = account.getNumber();
    this.creationDate = account.getCreationDate();
    this.balance = account.getBalance();
    this.transactions = account.getTransactions()
            .stream()
            .map(transaction -> new TransactionDto(transaction))
            .collect(java.util.stream.Collectors.toSet());
  }

  public Long getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public Double getBalance() {
    return balance;
  }

  public Set<TransactionDto> getTransactions() {
    return transactions;
  }
}

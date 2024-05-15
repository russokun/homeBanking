package com.mindhub.homebanking.dtos;
import com.mindhub.homebanking.models.ClientLoans;

public class ClientLoansDto {
  private long id;

  private long loanId;

  private String loanName;

  private int payments;

  private double amount;

  public ClientLoansDto(ClientLoans clientLoans) {
    this.id = clientLoans.getId();
    this.loanId = clientLoans.getLoans().getId();
    this.loanName = clientLoans.getLoans().getName();
    this.payments = clientLoans.getPayments();
    this.amount = clientLoans.getAmount();
  }

  public long getId() {
    return id;
  }

  public long getLoanId() {
    return loanId;
  }

  public String getLoanName() {
    return loanName;
  }

  public int getPayments() {
    return payments;
  }

  public double getAmount() {
    return amount;
  }
}

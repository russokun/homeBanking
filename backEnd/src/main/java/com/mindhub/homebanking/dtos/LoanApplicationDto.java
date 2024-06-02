package com.mindhub.homebanking.dtos;

public class LoanApplicationDto {
  private Long loanId;
  private Double amount;
  private Integer installments;
  private String destinationAccountNumber;
  private String client;

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public Integer getInstallments() {
    return installments;
  }

  public void setInstallments(Integer installments) {
    this.installments = installments;
  }

  public String getDestinationAccountNumber() {
    return destinationAccountNumber;
  }

  public void setDestinationAccountNumber(String destinationAccountNumber) {
    this.destinationAccountNumber = destinationAccountNumber;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Long getLoanId() {
    return loanId;
  }

  public void setLoanId(Long loanId) {
    this.loanId = loanId;
  }


}

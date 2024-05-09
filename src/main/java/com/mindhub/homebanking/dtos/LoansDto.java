package com.mindhub.homebanking.dtos;
import com.mindhub.homebanking.models.ClientLoans;
import com.mindhub.homebanking.models.Loans;

import java.util.HashSet;
import java.util.Set;

public class LoansDto {
  private long id;

  private String name;

  private int maxAmmount;

  private Set<Integer> payments = new HashSet<>();

  public LoansDto(Loans Loans) {
    this.id = Loans.getId();
    this.name = Loans.getName();
    this.maxAmmount = Loans.getMaxAmmount();
    this.payments = Loans.getPayments();
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getMaxAmmount() {
    return maxAmmount;
  }

  public Set<Integer> getPayments() {
    return payments;
  }
}

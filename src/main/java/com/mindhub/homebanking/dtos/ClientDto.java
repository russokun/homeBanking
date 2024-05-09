package com.mindhub.homebanking.dtos;
import com.mindhub.homebanking.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Set<AccountDto> accounts;
  private Set<LoansDto> loans;
  public ClientDto(Client client) {
    this.id = client.getId();
    this.firstName = client.getFirstName();
    this.lastName = client.getLastName();
    this.email = client.getEmail();
    this.accounts = client.getAccounts()
            .stream()
            .map(account -> new AccountDto(account))
            .collect(Collectors.toSet());
    this.loans = client.getClientLoans() // Asegúrate de tener un getter para clientLoans en Client
            .stream()
            .map(clientLoan -> new LoansDto(clientLoan.getLoans())) // Asegúrate de tener un getter para loans en ClientLoans
            .collect(Collectors.toSet());
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public Set<AccountDto> getAccounts() {
    return accounts;
  }

  public Set<LoansDto> getLoans() {
    return loans;
  }
}

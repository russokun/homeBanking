package com.mindhub.homebanking.services;


import com.mindhub.homebanking.dtos.LoanApplicationDto;
import com.mindhub.homebanking.dtos.LoansDto;
import com.mindhub.homebanking.models.Loans;

import java.util.List;

public interface LoanService {
  Loans findById(Long id);
  void createClientLoan(String username, Loans loan, double amount, int installments, LoanApplicationDto loanApplicationDto);

  List<LoansDto> getAllLoans();
}
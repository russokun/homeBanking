// src/main/java/com/mindhub/homebanking/services/implement/LoanServiceImpl.java
package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.dtos.LoanApplicationDto;
import com.mindhub.homebanking.dtos.LoansDto;
import com.mindhub.homebanking.models.Loans;
import com.mindhub.homebanking.repositories.LoansRepository;
import com.mindhub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.ClientLoans;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientLoansRepository;
import com.mindhub.homebanking.repositories.LoansRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientLoanService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

  @Autowired
  private LoansRepository loansRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountService accountService;

  @Autowired
  private ClientLoanService clientLoanService;

  @Autowired
  private TransactionService transactionService;

  @Override
  public Loans findById(Long id) {
    return loansRepository.findById(id).orElse(null);
  }

  @Transactional
  @Override
  public void createClientLoan(String username, Loans loan, double amount, int installments,LoanApplicationDto loanApplicationDto) {
    String accountNumber = loanApplicationDto.getDestinationAccountNumber();
    Account account = accountRepository.findByNumber(accountNumber);
    if (account != null) {
      ClientLoans clientLoan = new ClientLoans();
      clientLoan.setLoans(loan);
      clientLoan.setAmount(amount + amount * 0.2); // tasa d intere
      clientLoan.setPayments(installments);
      clientLoan.setClient(account.getClient());
      clientLoanService.save(clientLoan);

    }
     else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
    }
    }


  @Override
  public List<LoansDto> getAllLoans() {
    return loansRepository.findAll().stream()
            .map(loan -> new LoansDto(loan))
            .collect(Collectors.toList());
  }
}
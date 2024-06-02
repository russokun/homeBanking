package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.dtos.LoanApplicationDto;
import com.mindhub.homebanking.dtos.LoansDto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Loans;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.implement.AccountServiceImpl;
import com.mindhub.homebanking.services.implement.LoanServiceImpl;
import com.mindhub.homebanking.services.implement.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.mindhub.homebanking.services.LoanService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanControler {

  @Autowired
  private LoanServiceImpl loanServiceImpl;

  @Autowired
  private AccountServiceImpl accountServiceImpl;

  @Autowired
  private TransactionServiceImpl transactionServiceImpl;
  @Autowired
  private TransactionRepository transactionRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<String> applyForLoan(@RequestBody LoanApplicationDto loanApplication, @AuthenticationPrincipal UserDetails userDetails) {
    // Validar datos
    if (loanApplication.getAmount() <= 0 || loanApplication.getInstallments() <= 0) {
      return ResponseEntity.badRequest().body("Amount and installments must be greater than zero.");
    }

    // Verificar existencia del préstamo
    Loans loan = loanServiceImpl.findById(loanApplication.getLoanId());
    if (loan == null) {
      return ResponseEntity.badRequest().body("Loan does not exist.");
    }

    // Verificar monto y cuotas
    if (loanApplication.getAmount() > loan.getMaxAmmount()) {
      return ResponseEntity.badRequest().body("Requested amount exceeds maximum loan amount.");
    }
    if (!loan.getPayments().contains(loanApplication.getInstallments())) {
      return ResponseEntity.badRequest().body("Installments number is not available.");
    }

    // Verificar cuenta destino
    Account destinationAccount = accountServiceImpl.findByNumber(loanApplication.getDestinationAccountNumber());
    if (destinationAccount == null) {
      return ResponseEntity.badRequest().body("Destination account does not exist.");
    }
    if (!destinationAccount.getClient().getEmail().equals(userDetails.getUsername())) {
      return ResponseEntity.badRequest().body("Destination account does not belong to authenticated client.");
    }

    // Crear solicitud de préstamo
    double totalAmount = loanApplication.getAmount();
    loanServiceImpl.createClientLoan(userDetails.getUsername(), loan, totalAmount, loanApplication.getInstallments(),loanApplication);

    // Crear transacción
    Transaction transaction = new Transaction();
    transaction.setDescription("Loan approved: " + loan.getName());
    transaction.setAmount(totalAmount);
    transaction.setDate(LocalDateTime.now());
    transaction.setType(TransactionType.CREDIT);
    transaction.setClientAccount(destinationAccount);
    transactionRepository.save(transaction);

    transactionServiceImpl.save(transaction);

    // Actualizar cuenta destino
    accountServiceImpl.updateBalance(destinationAccount.getId(), totalAmount);

    return ResponseEntity.ok("Loan approved successfully.");
  }

  @GetMapping
  public ResponseEntity<List<LoansDto>> getAvailableLoans() {
    List<LoansDto> loans = loanServiceImpl.getAllLoans();
    return ResponseEntity.ok(loans);
  }
}




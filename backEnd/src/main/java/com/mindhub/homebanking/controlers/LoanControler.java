package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.dtos.LoanApplicationDto;
import com.mindhub.homebanking.dtos.LoansDto;
import com.mindhub.homebanking.models.*;

import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.TransactionService;
import com.mindhub.homebanking.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  private LoanService loanService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private SecurityUtils securityUtils;



  @PostMapping
  @Transactional
  public ResponseEntity<String> applyForLoan(@RequestBody LoanApplicationDto loanApplication, @AuthenticationPrincipal UserDetails userDetails) {

    Client client = securityUtils.getAuthenticatedClient();

    // Verificar si el cliente tiene una cuenta
    if (client.getAccounts().isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Client does not have an account.");
    }

    // Verificar si el cliente ya tiene un préstamo
    if (!client.getClientLoans().isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Client already has a loan.");
    }

    Integer installments = loanApplication.getInstallments();
    if (installments == null) {
      throw new IllegalArgumentException("Installments cannot be null");
    }
    int installmentsValue = installments.intValue();

    // Validar datos
    if (loanApplication.getAmount() <= 0 || loanApplication.getInstallments() <= 0) {
      return ResponseEntity.badRequest().body("Amount and installments must be greater than zero.");
    }

    // Verificar existencia del préstamo
    Loans loan = loanService.findById(loanApplication.getLoanId());
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
    Account destinationAccount = accountService.findByNumber(loanApplication.getDestinationAccountNumber());
    if (destinationAccount == null) {
      return ResponseEntity.badRequest().body("Destination account does not exist.");
    }
    if (!destinationAccount.getClient().getEmail().equals(userDetails.getUsername())) {
      return ResponseEntity.badRequest().body("Destination account does not belong to authenticated client.");
    }

    // Crear solicitud de préstamo
    double totalAmount = loanApplication.getAmount();
    loanService.createClientLoan(userDetails.getUsername(), loan, totalAmount, loanApplication.getInstallments(),loanApplication);

    // Crear transacción
    Transaction transaction = new Transaction();
    transaction.setDescription("Loan approved: " + loan.getName());
    transaction.setAmount(totalAmount);
    transaction.setDate(LocalDateTime.now());
    transaction.setType(TransactionType.CREDIT);
    transaction.setClientAccount(destinationAccount);
    transaction.addClientAccount(destinationAccount);
    transactionService.save(transaction);

    //transactionService.save(transaction);

    // Actualizar cuenta destino
    accountService.updateBalance(destinationAccount.getId(), totalAmount);

    return ResponseEntity.ok("Loan approved successfully.");
  }

  @GetMapping
  public ResponseEntity<List<LoansDto>> getAvailableLoans() {
    List<LoansDto> loans = loanService.getAllLoans();
    return ResponseEntity.ok(loans);
  }
}




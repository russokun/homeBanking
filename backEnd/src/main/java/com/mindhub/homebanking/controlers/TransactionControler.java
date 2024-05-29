package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.dtos.TransferDto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControler {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private TransactionRepository transactionRepository;


// ...

  @Transactional
  @PostMapping
  public ResponseEntity<?> createTransaction(@RequestBody TransferDto transferDto, Authentication authentication) {
    // Verificar que los parámetros no estén vacíos
    if (transferDto.getAmount() == null || transferDto.getDescription() == null || transferDto.getSourceAccountNumber() == null || transferDto.getDestinationAccountNumber() == null) {
      return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
    }

    // Verificar que los números de cuenta no sean los mismos
    if (transferDto.getSourceAccountNumber().equals(transferDto.getDestinationAccountNumber())) {
      return new ResponseEntity<>("Source and destination accounts cannot be the same", HttpStatus.BAD_REQUEST);
    }

    // Verificar que la cuenta de origen exista
    Account sourceAccount = accountRepository.findByNumber(transferDto.getSourceAccountNumber());
    if (sourceAccount == null) {
      return new ResponseEntity<>("Source account not found", HttpStatus.NOT_FOUND);
    }

    // Verificar que la cuenta de origen pertenezca al cliente autenticado
    if (!sourceAccount.getClient().getEmail().equals(authentication.getName())) {
      return new ResponseEntity<>("Source account does not belong to authenticated client", HttpStatus.FORBIDDEN);
    }

    // Verificar que la cuenta de destino exista
    Account destinationAccount = accountRepository.findByNumber(transferDto.getDestinationAccountNumber());
    if (destinationAccount == null) {
      return new ResponseEntity<>("Destination account not found", HttpStatus.NOT_FOUND);
    }

    // Verificar que la cuenta de origen tenga el monto disponible.
    if (sourceAccount.getBalance() < transferDto.getAmount()) {
      return new ResponseEntity<>("Insufficient funds in source account", HttpStatus.FORBIDDEN);
    }

    // Crear dos transacciones, una con el tipo de transacción “DÉBITO” asociada a la cuenta de origen
    // y la otra con el tipo de transacción “CRÉDITO” asociada a la cuenta de destino.
    Transaction debitTransaction = new Transaction(transferDto.getDescription() + " " + sourceAccount.getNumber(), -transferDto.getAmount(), LocalDateTime.now(), TransactionType.DEBIT, sourceAccount);
    Transaction creditTransaction = new Transaction(transferDto.getDescription() + " " + destinationAccount.getNumber(), transferDto.getAmount(), LocalDateTime.now(), TransactionType.CREDIT, destinationAccount);

    // El monto indicado en la solicitud se restará de la cuenta de origen y el mismo monto se sumará a la cuenta de destino.
    sourceAccount.setBalance(sourceAccount.getBalance() - transferDto.getAmount());
    destinationAccount.setBalance(destinationAccount.getBalance() + transferDto.getAmount());

    // Guardar las transacciones en el repositorio de transacciones.
    transactionRepository.save(debitTransaction);
    transactionRepository.save(creditTransaction);

    // Guardar las cuentas en el repositorio de cuentas.
    accountRepository.save(sourceAccount);
    accountRepository.save(destinationAccount);

    return new ResponseEntity<>("Transaction created", HttpStatus.CREATED);
  }
}
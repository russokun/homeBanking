package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.dtos.TransferDto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.TransactionService;
import com.mindhub.homebanking.utils.MissingParameterException;
import com.mindhub.homebanking.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControler {

  @Autowired
  private AccountService accountService;

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private SecurityUtils securityUtils;



  @Transactional
  @PostMapping
  public ResponseEntity<?> createTransaction(@RequestBody TransferDto transferDto, Authentication authentication) throws MethodArgumentNotValidException {

    Client client = securityUtils.getAuthenticatedClient();

    // Verificar que los parámetros no estén vacíos
    if (transferDto.getAmount() == null || transferDto.getDescription() == null || transferDto.getDescription().trim().isEmpty()) {
      throw new MissingParameterException("Invalid or missing parameters");
    }

    if (transferDto.getAmount() <= 0) {
      return new ResponseEntity<>("Amount must be greater than zero", HttpStatus.BAD_REQUEST);
    }

    // Verificar que los números de cuenta no sean los mismos
    if (transferDto.getSourceAccountNumber().equals(transferDto.getDestinationAccountNumber())) {
      return new ResponseEntity<>("Source and destination accounts cannot be the same", HttpStatus.BAD_REQUEST);
    }

    // Verificar que la cuenta de origen exista
    Account sourceAccount = accountService.findByNumber(transferDto.getSourceAccountNumber());
    if (sourceAccount == null) {
      return new ResponseEntity<>("Source account not found", HttpStatus.NOT_FOUND);
    }

    // Verificar que la cuenta de origen pertenezca al cliente autenticado
    if (!sourceAccount.getClient().getEmail().equals(authentication.getName())) {
      return new ResponseEntity<>("Source account does not belong to authenticated client", HttpStatus.FORBIDDEN);
    }

    // Verificar que la cuenta de destino exista
    Account destinationAccount = accountService.findByNumber(transferDto.getDestinationAccountNumber());
    if (destinationAccount == null) {
      return new ResponseEntity<>("Destination account not found", HttpStatus.NOT_FOUND);
    }

    // Verificar que la cuenta de origen tenga el monto disponible.
    if (sourceAccount.getBalance() < transferDto.getAmount()) {
      return new ResponseEntity<>("Insufficient funds in source account", HttpStatus.FORBIDDEN);
    }

    // Crear dos transacciones, una con el tipo de transacción “DÉBITO” asociada a la cuenta de origen
    // y la otra con el tipo de transacción “CRÉDITO” asociada a la cuenta de destino.
    Transaction debitTransaction = new Transaction(transferDto.getDescription() + " to " + destinationAccount.getNumber(), -transferDto.getAmount(), LocalDateTime.now(), TransactionType.DEBIT, sourceAccount);
    Transaction creditTransaction = new Transaction(transferDto.getDescription() + "  from " + sourceAccount.getNumber(), transferDto.getAmount(), LocalDateTime.now(), TransactionType.CREDIT, destinationAccount);

    // El monto indicado en la solicitud se restará de la cuenta de origen y el mismo monto se sumará a la cuenta de destino.
    sourceAccount.setBalance(sourceAccount.getBalance() - transferDto.getAmount());
    destinationAccount.setBalance(destinationAccount.getBalance() + transferDto.getAmount());

    // Guardar las transacciones en el repositorio de transacciones.
    transactionService.save(debitTransaction);
    transactionService.save(creditTransaction);

    // Guardar las cuentas en el repositorio de cuentas.
    accountService.save(sourceAccount);
    accountService.save(destinationAccount);

    return new ResponseEntity<>("Transaction created", HttpStatus.CREATED);
  }
}
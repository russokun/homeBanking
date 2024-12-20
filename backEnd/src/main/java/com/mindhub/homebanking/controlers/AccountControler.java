package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.dtos.AccountDto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.utils.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class AccountControler {

  @Autowired
  private AccountService accountService;

  @Autowired
  private ClientService clientService;

  @GetMapping("/current/accounts")
  public ResponseEntity<?> getAccounts(Authentication authentication) {
    Client client = clientService.findByEmail(authentication.getName());
    List<Account> accountsList = accountService.findByClient(client);
    List<AccountDto> accountsDtoList = accountsList.stream().map(AccountDto::new).collect(Collectors.toList());

    if (!accountsList.isEmpty()) {
      return new ResponseEntity<>(accountsDtoList, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Client has no accounts", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/accounts/{id}")
  public ResponseEntity<?> getAccountById(@PathVariable Long id) {
    Optional<Account> optionalAccount = accountService.findById(id);
    if (optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      return new ResponseEntity<>(new AccountDto(account), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/current/accounts")
  public ResponseEntity<?> createAccountForAuthenticatedClient(Authentication authentication) {
    // Obtener el cliente actualmente autenticado
    Client client = clientService.findByEmail(authentication.getName());

    // Verificar si el cliente ya tiene 3 cuentas
    if (client.getAccounts().size() >= 3) {
      return new ResponseEntity<>("Client already has 3 accounts", HttpStatus.FORBIDDEN);
    }

    // Crear una nueva cuenta para el cliente
    Account newAccount = new Account();
    newAccount.setNumber(AccountNumberGenerator.generate());
    newAccount.setBalance(0);
    newAccount.setClient(client);
    newAccount.setCreationDate(LocalDate.now()); // Set the creation date to the current date
    accountService.save(newAccount);

    return new ResponseEntity<>("Account created for authenticated client", HttpStatus.CREATED);
  }
}


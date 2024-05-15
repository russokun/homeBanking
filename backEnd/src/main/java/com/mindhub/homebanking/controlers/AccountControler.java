package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.dtos.AccountDto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountControler {
  @Autowired
  AccountRepository accountRepository;
  @GetMapping("/")
  public ResponseEntity<?> getAccounts() {
    List<Account> accountsList = accountRepository.findAll();
    List <AccountDto> accountsDtoList = accountsList.stream().map(account -> new AccountDto(account)).collect(Collectors.toList());
    if (!accountsList.isEmpty()) {
      return new ResponseEntity<>(accountsDtoList, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("There are no accounts", HttpStatus.NOT_FOUND);
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getAccountById(@PathVariable Long id) {
    Optional<Account> optionalAccount = accountRepository.findById(id);
    if (optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      return new ResponseEntity<>(new AccountDto(account), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
    }
  }
}

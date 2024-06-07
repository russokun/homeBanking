// src/main/java/com/mindhub/homebanking/services/implement/AccountServiceImpl.java
package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Account findByNumber(String number) {
    return accountRepository.findByNumber(number);
  }

  @Override
  public void updateBalance(Long accountId, double amount) {
    Account account = accountRepository.findById(accountId).orElseThrow();
    account.setBalance(account.getBalance() + amount);
    accountRepository.save(account);
  }

  @Override
  public List<Account> findByClient(Client client) {
    return accountRepository.findByClient(client);
  }

  @Override
  public Optional<Account> findById(Long id) {
    return accountRepository.findById(id);
  }

  @Override
  public void save(Account newAccount) {
    accountRepository.save(newAccount);
  }
}
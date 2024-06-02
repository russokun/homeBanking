// src/main/java/com/mindhub/homebanking/services/implement/AccountServiceImpl.java
package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
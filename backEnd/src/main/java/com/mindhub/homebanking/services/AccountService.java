// src/main/java/com/mindhub/homebanking/services/AccountService.java
package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;

import java.util.List;
import java.util.Optional;

public interface AccountService {
  Account findByNumber(String number);
  void updateBalance(Long accountId, double amount);
  List<Account> findByClient(Client client);
  Optional<Account> findById(Long id);
  void save(Account newAccount);
}
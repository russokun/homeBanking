// src/main/java/com/mindhub/homebanking/services/AccountService.java
package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Account;

public interface AccountService {
  Account findByNumber(String number);
  void updateBalance(Long accountId, double amount);
}
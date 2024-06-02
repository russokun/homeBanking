// src/main/java/com/mindhub/homebanking/services/TransactionService.java
package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Transaction;

public interface TransactionService {
  void save(Transaction transaction);
}
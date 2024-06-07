// src/main/java/com/mindhub/homebanking/services/TransactionService.java
package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

public interface TransactionService {
  void save(Transaction transaction);
  //void createAndSaveTransaction(String description, double totalAmount, TransactionType transactionType, Account destinationAccount);
}
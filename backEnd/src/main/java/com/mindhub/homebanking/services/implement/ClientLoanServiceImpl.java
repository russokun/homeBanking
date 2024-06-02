// src/main/java/com/mindhub/homebanking/services/implement/ClientLoanServiceImpl.java
package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.models.ClientLoans;
import com.mindhub.homebanking.repositories.ClientLoansRepository;
import com.mindhub.homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImpl implements ClientLoanService {

  @Autowired
  private ClientLoansRepository clientLoansRepository;

  @Override
  public void save(ClientLoans clientLoan) {
    clientLoansRepository.save(clientLoan);
  }
}
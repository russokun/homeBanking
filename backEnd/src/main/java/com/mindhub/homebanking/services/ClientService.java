package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
  Client findByEmail(String email);
  void save(Client client);
  List<Client> findAll();
  Optional<Client> findById(Long id);
}

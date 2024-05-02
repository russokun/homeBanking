package com.mindhub.homebanking.controlers;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientControler {

  @Autowired
  ClientRepository clientRepository;

  @GetMapping("/hello")
  public String getClients() {
    return "Hello Clients from API!";
  }

  @GetMapping("/")
  public List <Client> getAllClients() {
    return clientRepository.findAll();
  }
}

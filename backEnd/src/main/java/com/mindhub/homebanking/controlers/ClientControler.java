package com.mindhub.homebanking.controlers;
import com.mindhub.homebanking.dtos.ClientDto;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/clients")
public class ClientControler {

  @Autowired
  ClientService clientService;

  //@GetMapping("/hello")
  //public String getClients() {return "Hello Clients from API!";}

  @GetMapping()
  public ResponseEntity<?> getAllClients() {
    List <Client> clientsList = clientService.findAll();
    List <ClientDto> clientsDtoList = clientsList.stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
    if (!clientsList.isEmpty()) {
      return new ResponseEntity<>(clientsDtoList, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("There are no clients", HttpStatus.NOT_FOUND);
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getClient(@PathVariable Long id) {//path variable es un parametro que se pasa en la url
    Client client = (Client) clientService.findById(id).orElse(null);
    if (client != null) {
      ClientDto clientDto = new ClientDto(client);
      return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
    }
  }
}

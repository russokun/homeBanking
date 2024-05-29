package com.mindhub.homebanking.controlers;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.dtos.CardRequestDto;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping("/api/clients")
public class CardControler {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private CardRepository cardRepository;

  @PostMapping("/current/cards")
  public ResponseEntity<?> createCardForAuthenticatedClient(@RequestBody CardRequestDto cardRequest, Authentication authentication) {
    // Obtener el cliente actualmente autenticado
    Client client = clientRepository.findByEmail(authentication.getName());

    // Verificar si el cliente ya tiene 3 tarjetas del tipo especificado
    long count = client.getCards().stream()
            .filter(card -> card.getType() == cardRequest.getType())
            .count();
    if (count >= 3) {
      return new ResponseEntity<>("Client already has 3 cards of this type", HttpStatus.FORBIDDEN);
    }

    // Crear una nueva tarjeta para el cliente
    Card newCard = new Card();
    newCard.setCardHolder(client.getFirstName() + " " + client.getLastName());
    newCard.setNumber(generateCardNumber());
    newCard.setCvv(generateCvv());
    newCard.setFromDate(LocalDate.now());
    newCard.setDueDate(LocalDate.now().plusYears(5));
    newCard.setType(cardRequest.getType());
    newCard.setColor(cardRequest.getColor());
    newCard.setClient(client);
    cardRepository.save(newCard);

    return new ResponseEntity<>("Card created for authenticated client", HttpStatus.CREATED);
  }

  private String generateCardNumber() {
    Random random = new Random();
    return String.format("%04d-%04d-%04d-%04d", random.nextInt(10000), random.nextInt(10000), random.nextInt(10000), random.nextInt(10000));
  }

  private int generateCvv() {
    Random random = new Random();
    return random.nextInt(900) + 100; // Esto generará un número aleatorio de 3 dígitos
  }
} // FALTA GET MAPPING PARA OBTENER TODAS LAS TARJETAS DE UN CLIENTE
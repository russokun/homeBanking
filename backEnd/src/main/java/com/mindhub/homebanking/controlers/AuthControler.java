package com.mindhub.homebanking.controlers;


import com.mindhub.homebanking.dtos.ClientDto;
import com.mindhub.homebanking.dtos.LoginDto;
import com.mindhub.homebanking.dtos.RegisterDto;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.implement.AccountServiceImpl;
import com.mindhub.homebanking.services.implement.ClientServiceImpl;
import com.mindhub.homebanking.servicesSecurity.JwtUtilService;
import com.mindhub.homebanking.utils.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
public class AuthControler {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private ClientService clientService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtUtilService jwtUtilService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto loginDto) { //para vincular el cuerpo de una solicitud HTTP entrante a un objeto en un controlador.
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));//autenticamos el usuario y contraseña
      final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.email());//cargamos los datos del usuario en memoria para token
      final String jwt = jwtUtilService.generateToken(userDetails); //generacion de token
      return ResponseEntity.ok(jwt);
    } catch (Exception e) {
      return new ResponseEntity<>("Email or password invalid", HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
    if(registerDto.firstName().isBlank()){
      return new ResponseEntity<>("First name is required", HttpStatus.FORBIDDEN);
    }
    if(registerDto.lastName().isBlank()){
      return new ResponseEntity<>("Last name is required", HttpStatus.FORBIDDEN);
    }
    if(registerDto.email().isBlank()){
      return new ResponseEntity<>("Email is required", HttpStatus.FORBIDDEN);
    }
    if(registerDto.password().isBlank()){
      return new ResponseEntity<>("Password is required", HttpStatus.FORBIDDEN);
    }
    if(clientService.findByEmail(registerDto.email()) != null){
      return new ResponseEntity<>("Email already exists", HttpStatus.FORBIDDEN);
    }

    Client client = new Client(
      registerDto.firstName(),
      registerDto.lastName(), registerDto.email(),
      passwordEncoder.encode(registerDto.password()));
    clientService.save(client);

    Account newAccount = new Account();
    newAccount.setNumber(AccountNumberGenerator.generate());
    newAccount.setBalance(0);
    newAccount.setClient(client);
    newAccount.setCreationDate(LocalDate.now());
    accountService.save(newAccount);

    return new ResponseEntity<>("Client and account created",HttpStatus.CREATED);

  }

  @GetMapping("/current")
  public ResponseEntity<?> getClient(Authentication authentication){
    Client client = clientService.findByEmail(authentication.getName());
    return  ResponseEntity.ok(new ClientDto(client));
  }
}

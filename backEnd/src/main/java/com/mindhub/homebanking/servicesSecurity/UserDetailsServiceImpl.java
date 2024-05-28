package com.mindhub.homebanking.servicesSecurity;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// class para implementar un Usuario
@Service //marca la clase como un servicio de Spring
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //sobreescribe el metodo loadbyusername
    //devuelve un UserDetails que representa al usuario que queremos tener en la sesion
    Client client = clientRepository.findByEmail(username);

    if (client == null) { //si el cliente no existe..
      throw new UsernameNotFoundException(username);
    }

    return User //si client existe..
      .withUsername(client.getEmail())
      .password(client.getPassword())
      .roles("CLIENT")
      .build();
  }


}

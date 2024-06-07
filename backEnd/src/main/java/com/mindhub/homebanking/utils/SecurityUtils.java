package com.mindhub.homebanking.utils;

import com.mindhub.homebanking.servicesSecurity.UserDetailsServiceImpl;
import com.mindhub.homebanking.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  public Client getAuthenticatedClient() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    // Assuming you have a method in your userDetailsService to get Client by username
    Client client = userDetailsService.findClientByUsername(currentPrincipalName);
    return client;
  }
}
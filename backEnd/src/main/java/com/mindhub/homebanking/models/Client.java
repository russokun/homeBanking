package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  public String password;

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<Account> accounts = new HashSet<>();

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<ClientLoans> clientLoans = new HashSet<>();

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<Card> cards = new HashSet<>();

  public Client() {
  }

  public Client(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Client(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public String getPassword() {return password;}

  public void setPassword(String password) {this.password = password;}

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<ClientLoans> getClientLoans() {
    return clientLoans;
  }

  public void setClientLoans(Set<ClientLoans> clientLoans) {
    this.clientLoans = clientLoans;
  }

  public void addClientLoans(ClientLoans clientLoan) {
    clientLoan.setClient(this);
    this.clientLoans.add(clientLoan);
  }

  public Set<Account> getAccounts() {
    return accounts;
  }

  public void addAccount(Account account) {
    account.setClient(this);
    accounts.add(account);
  }

  public Set<Card> getCards() {
    return cards;
  }

  public void addCard(Card card) {
    card.setClient(this);
    cards.add(card);
    card.setCardHolder(this.getFirstName() + " " + this.getLastName());
  }

  @Override
  public String toString() {
    return "Client{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", accounts='" +accounts  + '\'' +
            ", clientLoans='" + clientLoans + '\'' +
            ", cards='" + cards + '\'' +
            '}';
  }
}



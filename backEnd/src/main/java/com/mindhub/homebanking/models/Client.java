package com.mindhub.homebanking.models;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
//La encapsulación es el mecanismo que restringe el acceso directo a algunos componentes de un objeto,
//lo que significa que los detalles internos del objeto están ocultos.
//Solo se puede acceder a esos componentes a través de métodos definidos o accesores (getters y setters).
//La encapsulación proporciona un mayor control sobre los datos y ayuda a mantener la integridad del objeto.
//Ventajas:
//
//Protección de datos: Previene la manipulación directa de los datos.
//Modularidad: Facilita la modificación y el mantenimiento del código.
//Flexibilidad: Permite cambiar la implementación interna sin afectar a los usuarios del objeto.


@Entity
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  public String password;
  private Boolean isAdmin = false;

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

  public Boolean getAdmin() {
    return isAdmin;
  }

  public Boolean setAdmin(Boolean admin) {
    isAdmin = admin;
    return admin;
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



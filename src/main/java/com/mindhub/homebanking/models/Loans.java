package com.mindhub.homebanking.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Loans {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private int maxAmmount;

  @ElementCollection
  private Set<Integer> payments = new HashSet<>();

  @OneToMany(mappedBy = "loans", fetch = FetchType.EAGER)
  private Set<ClientLoans> clientsLoans = new HashSet<>();

  public Loans() {
  }

  public Loans(Set<Integer> payments, String name, int maxAmmount) {
    this.payments = payments;
    this.name = name;
    this.maxAmmount = maxAmmount;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMaxAmmount() {
    return maxAmmount;
  }

  public void setMaxAmmount(int maxAmmount) {
    this.maxAmmount = maxAmmount;
  }

  public Set<Integer> getPayments() {
    return payments;
  }

  public void setPayments(Set<Integer> payments) {
    this.payments = payments;
  }

  public Set<ClientLoans> getClientsLoans() {
    return clientsLoans;
  }

  public void setClientsLoans(Set<ClientLoans> clientsLoans) {
    this.clientsLoans = clientsLoans;
  }

  public void addClientLoans(ClientLoans clientLoans) {
    clientLoans.setLoans(this);
    clientsLoans.add(clientLoans);
  }

  @Override
  public String toString() {
    return "Loans{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", maxAmmount=" + maxAmmount +
            ", payments=" + payments +
            '}';
  }
}

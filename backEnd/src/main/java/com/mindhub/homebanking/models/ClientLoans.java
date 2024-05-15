package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.mindhub.homebanking.models.Loans;

@Entity
public class ClientLoans {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private int payments;

  private double amount;

  @ManyToOne
  private Client client;

  @ManyToOne
  private Loans loans;

  public ClientLoans() {
  }

  public ClientLoans(int payments, double amount) {
    this.payments = payments;
    this.amount = amount;
  }

  public long getId() {
    return id;
  }

  public int getPayments() {
    return payments;
  }

  public void setPayments(int payments) {
    this.payments = payments;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Loans getLoans() {
    return loans;
  }

  public void setLoans(Loans loans) {
    this.loans = loans;
  }
}

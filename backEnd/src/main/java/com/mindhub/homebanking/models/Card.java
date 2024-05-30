package com.mindhub.homebanking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private CardColor color;

  private CardType type;

  private String number;

  private String cardHolder;

  private LocalDate fromDate;

  private LocalDate dueDate;

  private int cvv;

  @JsonBackReference
  @ManyToOne
  private Client client;

  public Card() {
  }

  public Card(CardColor color, CardType type, String number, LocalDate fromDate, LocalDate dueDate, int cvv) {
    this.color = color;
    this.type = type;
    this.number = number;
    this.fromDate = fromDate;
    this.dueDate = dueDate;
    this.cvv = cvv;
  }

  public long getId() {
    return id;
  }

  public CardColor getColor() {
    return color;
  }

  public void setColor(CardColor color) {
    this.color = color;
  }

  public CardType getType() {
    return type;
  }

  public void setType(CardType type) {
    this.type = type;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public int getCvv() {
    return cvv;
  }

  public void setCvv(int cvv) {
    this.cvv = cvv;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  @Override
  public String toString() {
    return "Card{" +
            "id=" + id +
            ", color=" + color +
            ", type=" + type +
            ", number='" + number + '\'' +
            ", cardHolder='" + cardHolder + '\'' +
            ", fromDate=" + fromDate +
            ", dueDate=" + dueDate +
            ", cvv=" + cvv +
            '}';
  }
}

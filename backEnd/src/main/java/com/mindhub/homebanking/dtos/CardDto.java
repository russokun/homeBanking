package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Card;

import java.time.LocalDate;

public class CardDto {
  private long id;

  private CardColor color;

  private CardType type;

  private String number;

  private String cardHolder;

  private LocalDate fromDate;

  private LocalDate dueDate;

  private int cvv;

  public CardDto(Card card) {
    this.id = card.getId();
    this.color = card.getColor();
    this.type = card.getType();
    this.number = card.getNumber();
    this.cardHolder = card.getCardHolder();
    this.fromDate = card.getFromDate();
    this.dueDate = card.getDueDate();
    this.cvv = card.getCvv();
  }

  public long getId() {
    return id;
  }

  public CardColor getColor() {
    return color;
  }

  public CardType getType() {
    return type;
  }

  public String getNumber() {
    return number;
  }

  public String getCardHolder() {
    return cardHolder;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public int getCvv() {
    return cvv;
  }
}

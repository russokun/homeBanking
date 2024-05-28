package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

public class CardRequestDto {
  private CardType type;
  private CardColor color;

  // Getters y setters
  public CardType getType() {
    return type;
  }

  public void setType(CardType type) {
    this.type = type;
  }

  public CardColor getColor() {
    return color;
  }

  public void setColor(CardColor color) {
    this.color = color;
  }
}
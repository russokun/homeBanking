package com.mindhub.homebanking.utils;

import java.util.Random;

public class AccountNumberGenerator {
  private static final Random RANDOM = new Random();

  public static String generate() {
    int number = 10000000 + RANDOM.nextInt(90000000);  // Genera un número aleatorio de 8 dígitos
    return "VIN-" + number;
  }
}
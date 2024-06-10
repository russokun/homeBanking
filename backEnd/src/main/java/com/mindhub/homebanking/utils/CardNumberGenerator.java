package com.mindhub.homebanking.utils;

import java.util.Random;

public class CardNumberGenerator {
  private static final Random random = new Random();

  public static String generate() {
    return String.format("%04d-%04d-%04d-%04d", random.nextInt(10000)
            , random.nextInt(10000)
            , random.nextInt(10000)
            , random.nextInt(10000));
  }
}

package com.soebes.kata.parser;

import com.soebes.kata.fraction.Fraction;

public final class ParseIntoFraction {

  private ParseIntoFraction() {
    // intentionally empty.
  }

  /**
   * @param fractionString The Fraction like {@code 4/5}
   * @return {@link Fraction}
   */
  public static Fraction parseFraction(String fractionString) {
    String[] split = fractionString.split("/");
    int numerator = Integer.parseInt(split[0]);
    int denominator = Integer.parseInt(split[1]);
    return new Fraction(numerator, denominator);
  }
}

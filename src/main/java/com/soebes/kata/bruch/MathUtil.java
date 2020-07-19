package com.soebes.kata.bruch;

import java.math.BigInteger;

public class MathUtil {

  private MathUtil() {
    // Absichtlich keine Instanz bildbar.
  }

  /**
   * @param zaehler Zaehler
   * @param nenner Nenner
   * @return kleinste gemeinsames Vielfache.
   */
  public static int berechneKgv(int zaehler, int nenner) {
    return BigInteger.valueOf(zaehler).gcd(BigInteger.valueOf(nenner)).intValueExact();
  }
}

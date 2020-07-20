package com.soebes.kata.bruch;

import java.math.BigInteger;

public class MathUtil {

  private MathUtil() {
    // Absichtlich keine Instanz bildbar.
  }

  /**
   * @param numerator Numerator
   * @param denominator Denominator
   * @return greatest common divisor.
   */
  public static int calculateGcd(int numerator, int denominator) {
    return BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValueExact();
  }

  /**
   * @param numerator Numerator
   * @param denominator Denominator
   * @return greatest common divisor.
   */
  public static int calculateGcd(long numerator, long denominator) {
    return BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValueExact();
  }
}

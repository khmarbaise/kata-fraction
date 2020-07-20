package com.soebes.kata.fraction;

import java.math.BigInteger;
import java.util.Objects;
import java.util.StringJoiner;

public class Fraction implements Comparable<Fraction> {
  private final int numerator;
  private final int denominator;

  public Fraction(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("denominator is not allowed to be zero.");
    }
    if (numerator == 0) {
      this.numerator = 0;
      this.denominator = 1;
    } else {
      int sign = Integer.signum(numerator) * Integer.signum(denominator);

      int gcd = MathUtil.calculateGcd(numerator, denominator);
      this.numerator = sign * Math.abs(numerator) / gcd;
      this.denominator = Math.abs(denominator) / gcd;
    }
  }

  public Fraction add(Fraction add) {
    if (this.denominator == add.denominator) {
      return new Fraction(add.numerator + this.numerator, this.denominator);
    } else {
      return new Fraction(add.numerator * this.denominator + this.numerator * add.denominator, add.denominator * this.denominator);
    }
  }

  public Fraction subtract(Fraction subtrahend) {
    if (this.denominator == subtrahend.denominator) {
      return new Fraction(this.numerator - subtrahend.numerator, this.denominator);
    } else {
      return new Fraction(this.numerator * subtrahend.denominator - this.denominator * subtrahend.numerator, subtrahend.denominator * this.denominator);
    }
  }

  public Fraction multiply(Fraction factor) {
    return new Fraction(this.numerator * factor.numerator, this.denominator * factor.denominator);
  }

  public Fraction divide(Fraction divisor) {
    return new Fraction(this.numerator * divisor.denominator, this.denominator * divisor.numerator);
  }

  public Fraction pow(int power) {
    return new Fraction(BigInteger.valueOf(this.numerator).pow(power).intValueExact(), BigInteger.valueOf(this.denominator).pow(power).intValueExact());
  }

  public int numerator() {
    return numerator;
  }

  public int denominator() {
    return denominator;
  }

  @Override
  public int compareTo(Fraction compareTo) {
    if (compareTo == null) {
      throw new NullPointerException("compareTo is not allowed to be null.");
    }
    return this.subtract(compareTo).signum();
  }

  /**
   * Returns the signum function of this {@code Bruch}.
   *
   * @return -1, 0, or 1 as the value of this {@code Bruch}
   * is negative, zero, or positive.
   */
  public int signum() {
    return Integer.signum(numerator);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Fraction fraction = (Fraction) o;
    return numerator == fraction.numerator &&
        denominator == fraction.denominator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Fraction.class.getSimpleName() + "[", "]")
        .add("numerator=" + numerator)
        .add("denominator=" + denominator)
        .toString();
  }

}

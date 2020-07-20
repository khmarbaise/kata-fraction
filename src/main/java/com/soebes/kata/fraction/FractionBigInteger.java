package com.soebes.kata.fraction;

import java.math.BigInteger;
import java.util.Objects;
import java.util.StringJoiner;

public class FractionBigInteger implements Comparable<FractionBigInteger> {
  private final BigInteger numerator;
  private final BigInteger denominator;

  public FractionBigInteger(BigInteger numerator, BigInteger denominator) {
    if (denominator.compareTo(BigInteger.ZERO) == 0) {
      throw new IllegalArgumentException("denominator is not allowed to be zero.");
    }
    if (numerator.compareTo(BigInteger.ZERO) == 0) {
      this.numerator = BigInteger.ZERO;
      this.denominator = BigInteger.ONE;
    } else {
      BigInteger sign = BigInteger.valueOf(numerator.signum() * denominator.signum());

      BigInteger gcd = MathUtil.calculateGcd(numerator, denominator);
      this.numerator = sign.multiply(numerator.abs().divide(gcd));
      this.denominator = denominator.abs().divide(gcd);
    }
  }

  public FractionBigInteger add(FractionBigInteger add) {
    if (this.denominator.compareTo(add.denominator) == 0) {
      return new FractionBigInteger(add.numerator.add(this.numerator), this.denominator);
    } else {
      return new FractionBigInteger(add.numerator.multiply(this.denominator).add(this.numerator.multiply(add.denominator)), add.denominator.multiply(this.denominator));
    }
  }

  public FractionBigInteger subtract(FractionBigInteger subtrahend) {
    if (this.denominator.compareTo(subtrahend.denominator) == 0) {
      return new FractionBigInteger(this.numerator.subtract(subtrahend.numerator), this.denominator);
    } else {
      return new FractionBigInteger(this.numerator.multiply(subtrahend.denominator).subtract(this.denominator.multiply(subtrahend.numerator)), subtrahend.denominator.multiply(this.denominator));
    }
  }

  public FractionBigInteger multiply(FractionBigInteger factor) {
    return new FractionBigInteger(this.numerator.multiply(factor.numerator), this.denominator.multiply(factor.denominator));
  }

  public FractionBigInteger divide(FractionBigInteger divisor) {
    return new FractionBigInteger(this.numerator.multiply(divisor.denominator), this.denominator.multiply(divisor.numerator));
  }

  public FractionBigInteger pow(int power) {
    return new FractionBigInteger(this.numerator.pow(power), this.denominator.pow(power));
  }

  public BigInteger numerator() {
    return numerator;
  }

  public BigInteger denominator() {
    return denominator;
  }

  @Override
  public int compareTo(FractionBigInteger compareTo) {
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
    return numerator.signum();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FractionBigInteger fraction = (FractionBigInteger) o;
    return numerator == fraction.numerator &&
        denominator == fraction.denominator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FractionBigInteger.class.getSimpleName() + "[", "]")
        .add("numerator=" + numerator)
        .add("denominator=" + denominator)
        .toString();
  }

}

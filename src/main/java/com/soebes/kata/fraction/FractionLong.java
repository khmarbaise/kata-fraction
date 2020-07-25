package com.soebes.kata.fraction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.StringJoiner;

public class FractionLong implements Comparable<FractionLong> {
  private final long numerator;
  private final long denominator;

  public FractionLong(long numerator, long denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("denominator is not allowed to be zero.");
    }
    int sign = Long.signum(numerator) * Long.signum(denominator);

    long gcd = MathUtil.calculateGcd(numerator, denominator);
    this.numerator = sign * Math.abs(numerator) / gcd;
    this.denominator = Math.abs(denominator) / gcd;
  }

  public FractionLong add(FractionLong add) {
    if (this.denominator == add.denominator) {
      return new FractionLong(add.numerator + this.numerator, this.denominator);
    } else {
      return new FractionLong(add.numerator * this.denominator + this.numerator * add.denominator, add.denominator * this.denominator);
    }
  }

  public FractionLong subtract(FractionLong subtrahend) {
    if (this.denominator == subtrahend.denominator) {
      return new FractionLong(this.numerator - subtrahend.numerator, this.denominator);
    } else {
      return new FractionLong(this.numerator * subtrahend.denominator - this.denominator * subtrahend.numerator, subtrahend.denominator * this.denominator);
    }
  }

  public FractionLong multiply(FractionLong factor) {
    return new FractionLong(this.numerator * factor.numerator, this.denominator * factor.denominator);
  }

  public FractionLong divide(FractionLong divisor) {
    return new FractionLong(this.numerator * divisor.denominator, this.denominator * divisor.numerator);
  }

  public FractionLong pow(int power) {
    return new FractionLong(BigInteger.valueOf(this.numerator).pow(power).intValueExact(), BigInteger.valueOf(this.denominator).pow(power).intValueExact());
  }

  public long numerator() {
    return numerator;
  }

  public long denominator() {
    return denominator;
  }

  @Override
  public int compareTo(FractionLong compareTo) {
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
    return Long.signum(numerator);
  }

  public FractionLong negate() {
    return new FractionLong(Math.negateExact(this.numerator), this.denominator);
  }

  public double doubleValue() {
    return (double) this.numerator / (double) this.denominator;
  }

  public BigDecimal bigDecimalValue() {
    return BigDecimal.valueOf(this.numerator).divide(BigDecimal.valueOf(this.denominator));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FractionLong fraction = (FractionLong) o;
    return numerator == fraction.numerator &&
        denominator == fraction.denominator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FractionLong.class.getSimpleName() + "[", "]")
        .add("numerator=" + numerator)
        .add("denominator=" + denominator)
        .toString();
  }

}

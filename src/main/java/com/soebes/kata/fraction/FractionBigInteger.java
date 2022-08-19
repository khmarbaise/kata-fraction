package com.soebes.kata.fraction;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apiguardian.api.API;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.StringJoiner;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

/**
 * @author Karl Heinz Marbaise
 */
@API(status = EXPERIMENTAL)
public class FractionBigInteger implements Comparable<FractionBigInteger>, Operation<FractionBigInteger> {
  private final BigInteger numerator;
  private final BigInteger denominator;

  public FractionBigInteger(BigInteger numerator, BigInteger denominator) {
    if (denominator.compareTo(BigInteger.ZERO) == 0) {
      throw new IllegalArgumentException("denominator is not allowed to be zero.");
    }
    BigInteger sign = BigInteger.valueOf(numerator.signum() * (long) denominator.signum());

    BigInteger gcd = MathUtil.calculateGcd(numerator, denominator);
    this.numerator = sign.multiply(numerator.abs().divide(gcd));
    this.denominator = denominator.abs().divide(gcd);
  }

  public FractionBigInteger plus(FractionBigInteger add) {
    if (this.denominator.compareTo(add.denominator) == 0) {
      return new FractionBigInteger(add.numerator.add(this.numerator), this.denominator);
    } else {
      BigInteger lNumerator = add.numerator.multiply(this.denominator).add(this.numerator.multiply(add.denominator));
      BigInteger lDenominator = add.denominator.multiply(this.denominator);
      return new FractionBigInteger(lNumerator, lDenominator);
    }
  }

  public FractionBigInteger subtract(FractionBigInteger subtrahend) {
    if (this.denominator.compareTo(subtrahend.denominator) == 0) {
      return new FractionBigInteger(this.numerator.subtract(subtrahend.numerator), this.denominator);
    } else {
      BigInteger lNumerator = this.numerator.multiply(subtrahend.denominator).subtract(this.denominator.multiply(subtrahend.numerator));
      BigInteger lDenominator = subtrahend.denominator.multiply(this.denominator);
      return new FractionBigInteger(lNumerator, lDenominator);
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
      throw new IllegalArgumentException("compareTo is not allowed to be null.");
    }
    return this.subtract(compareTo).signum();
  }

  /**
   * Returns the signum function of this {@code FractionBigInteger}.
   *
   * @return -1, 0, or 1 as the value of this {@code FractionBigInteger}
   * is negative, zero, or positive.
   */
  public int signum() {
    return numerator.signum();
  }

  public FractionBigInteger negate() {
    return new FractionBigInteger(this.numerator.negate(), this.denominator);
  }

  public double doubleValue() {
    return this.numerator.divide(this.denominator).doubleValue();
  }

  public BigDecimal bigDecimalValue() {
    return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FractionBigInteger that = (FractionBigInteger) o;
    return Objects.equals(numerator, that.numerator) &&
        Objects.equals(denominator, that.denominator);
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

package com.soebes.kata.bruch;

import java.util.Objects;
import java.util.StringJoiner;

public class Bruch implements Comparable<Bruch> {
  private final int zaehler;
  private final int nenner;

  public Bruch(int zaehler, int nenner) {
    if (nenner == 0) {
      throw new IllegalArgumentException("nenner darf nicht 0 sein.");
    }
    if (zaehler == 0) {
      this.zaehler = 0;
      this.nenner = 1;
    } else {
      int vorzeichen = Integer.signum(zaehler) * Integer.signum(nenner);

      int kgv = MathUtil.berechneKgv(zaehler, nenner);
      this.zaehler = vorzeichen * Math.abs(zaehler) / kgv;
      this.nenner = Math.abs(nenner) / kgv;
    }
  }

  public Bruch addiere(Bruch add) {
    if (this.nenner == add.nenner) {
      return new Bruch(add.zaehler + this.zaehler, this.nenner);
    } else {
      return new Bruch(add.zaehler * this.nenner + this.zaehler * add.nenner, add.nenner * this.nenner);
    }
  }

  public Bruch subtrahiere(Bruch subtrahend) {
    if (this.nenner == subtrahend.nenner) {
      return new Bruch(this.zaehler - subtrahend.zaehler, this.nenner);
    } else {
      return new Bruch(this.zaehler * subtrahend.nenner - this.nenner * subtrahend.zaehler, subtrahend.nenner * this.nenner);
    }
  }

  public Bruch multipliziere(Bruch factor) {
    return new Bruch(this.zaehler * factor.zaehler, this.nenner * factor.nenner);
  }

  public Bruch dividieren(Bruch divisor) {
    return new Bruch(this.zaehler * divisor.nenner, this.nenner * divisor.zaehler);
  }

  public int zaehler() {
    return zaehler;
  }

  public int nenner() {
    return nenner;
  }

  @Override
  public int compareTo(Bruch vergleich) {
    if (vergleich == null) {
      throw new IllegalArgumentException("vergleich darf nicht null sein.");
    }
    return this.subtrahiere(vergleich).signum();
  }

  /**
   * Returns the signum function of this {@code Bruch}.
   *
   * @return -1, 0, or 1 as the value of this {@code Bruch}
   * is negative, zero, or positive.
   */
  public int signum() {
    return Integer.signum(zaehler);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bruch bruch = (Bruch) o;
    return zaehler == bruch.zaehler &&
        nenner == bruch.nenner;
  }

  @Override
  public int hashCode() {
    return Objects.hash(zaehler, nenner);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Bruch.class.getSimpleName() + "[", "]")
        .add("zaehler=" + zaehler)
        .add("nenner=" + nenner)
        .toString();
  }

}

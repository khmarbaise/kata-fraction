package com.soebes.kata.bruch;

import java.util.Objects;
import java.util.StringJoiner;

public class Bruch {
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
      int kgv = MathUtil.berechne_kgv(zaehler, nenner);
      this.zaehler = zaehler / kgv;
      this.nenner = nenner / kgv;
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
      return new Bruch(this.zaehler * subtrahend.nenner - this.nenner * subtrahend.zaehler , subtrahend.nenner * this.nenner);
    }
  }

  public Bruch multipliziere(Bruch factor) {
    return new Bruch(this.zaehler * factor.zaehler, this.nenner * factor.nenner);
  }

  public int getZaehler() {
    return zaehler;
  }

  public int getNenner() {
    return nenner;
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

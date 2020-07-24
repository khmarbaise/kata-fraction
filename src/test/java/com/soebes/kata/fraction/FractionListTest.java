package com.soebes.kata.fraction;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

class FractionListTest {

  private static final List<Fraction> LIST_OF_FRACTIONS = List.of(new Fraction(1, 2),
      new Fraction(2, 3),
      new Fraction(3, 4),
      new Fraction(4, 5));

  @Test
  void name() {
    assertThat(LIST_OF_FRACTIONS).containsExactly(new Fraction(1, 2),
        new Fraction(2, 3),
        new Fraction(3, 4),
        new Fraction(4, 5));
  }

  @Test
  void list_test() {
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(1);
      assertThat(fraction.denominator()).isEqualTo(2);
      assertThat(fraction).isEqualTo(new Fraction(1, 2));
    }, atIndex(0));
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(2);
      assertThat(fraction.denominator()).isEqualTo(3);
      assertThat(fraction).isEqualTo(new Fraction(2, 3));
    }, atIndex(1));
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(3);
      assertThat(fraction.denominator()).isEqualTo(4);
      assertThat(fraction).isEqualTo(new Fraction(3, 4));
    }, atIndex(2));
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(4);
      assertThat(fraction.denominator()).isEqualTo(5);
      assertThat(fraction).isEqualTo(new Fraction(4, 5));
    }, atIndex(3));
  }

}

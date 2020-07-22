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
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction33 -> {
      assertThat(fraction33.numerator()).isEqualTo(1);
      assertThat(fraction33.denominator()).isEqualTo(2);
      assertThat(fraction33).isEqualTo(new Fraction(1, 2));
    }, atIndex(0));
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction32 -> {
      assertThat(fraction32.numerator()).isEqualTo(2);
      assertThat(fraction32.denominator()).isEqualTo(3);
      assertThat(fraction32).isEqualTo(new Fraction(2, 3));
    }, atIndex(1));
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction31 -> {
      assertThat(fraction31.numerator()).isEqualTo(3);
      assertThat(fraction31.denominator()).isEqualTo(4);
      assertThat(fraction31).isEqualTo(new Fraction(3, 4));
    }, atIndex(2));
    assertThat(LIST_OF_FRACTIONS).satisfies(fraction3 -> {
      assertThat(fraction3.numerator()).isEqualTo(4);
      assertThat(fraction3.denominator()).isEqualTo(5);
      assertThat(fraction3).isEqualTo(new Fraction(4, 5));
    }, atIndex(3));
  }

}

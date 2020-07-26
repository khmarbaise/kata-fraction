package com.soebes.kata.parser;

import com.soebes.kata.fraction.Fraction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class ParseIntoFractionTest {

  static Stream<Arguments> fractionAsString() {
    return Stream.of(
        of("1/3", new Fraction(1, 3)),
        of("5/6", new Fraction(5, 6)),
        of("391/247", new Fraction(391, 247))
    );
  }

  @ParameterizedTest
  @MethodSource("fractionAsString")
  void several(String fractionString, Fraction expectedFraction) {
    Fraction fraction = ParseIntoFraction.parseFraction(fractionString);
    assertThat(fraction).isEqualTo(expectedFraction);
  }

}

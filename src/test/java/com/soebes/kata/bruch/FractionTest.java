package com.soebes.kata.bruch;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class FractionTest {

  @Nested
  class Signum {
    @Test
    void signum_for_z_pos_n_pos() {
      Fraction fraction = new Fraction(1, 2);
      assertThat(fraction.signum()).isOne();
    }

    @Test
    void signum_for_z_neg_n_pos() {
      Fraction fraction = new Fraction(-1, 2);
      assertThat(fraction.signum()).isNegative();
    }

    @Test
    void signum_for_z_pos_n_neg() {
      Fraction fraction = new Fraction(1, -2);
      assertThat(fraction.signum()).isNegative();
    }

    @Test
    void signum_for_z_neg_n_neg() {
      Fraction fraction = new Fraction(-1, -2);
      assertThat(fraction.signum()).isPositive();
    }
  }

  @Nested
  class CompareTo {
    @Test
    void fraction_one_identical_to_fraction_two() {
      Fraction fraction_eins = new Fraction(1, 2);
      Fraction fraction_zwei = new Fraction(1, 2);

      assertThat(fraction_eins).isEqualByComparingTo(fraction_zwei);
    }

    @Test
    void fraction_one_greater_than_fraction_two() {
      Fraction fraction_eins = new Fraction(1, 1);
      Fraction fraction_zwei = new Fraction(1, 2);

      assertThat(fraction_eins.compareTo(fraction_zwei)).isPositive();
    }

    @Test
    void fraction_one_less_than_fraction_two() {
      Fraction fraction_eins = new Fraction(1, 3);
      Fraction fraction_zwei = new Fraction(1, 2);

      assertThat(fraction_eins.compareTo(fraction_zwei)).isNegative();
    }

  }

  @Nested
  class Verification {
    @Test
    void hash_code_and_equals() {
      EqualsVerifier.forClass(Fraction.class).usingGetClass().verify();
    }

    @Test
    void check_to_string() {
      Fraction fraction = new Fraction(1, 2);
      assertThat(fraction).hasToString("Fraction[numerator=1, denominator=2]");
    }
  }

  @Nested
  class InvalideValues {
    @Test
    void denominator_is_not_allowed_to_be_zero() {
      assertThatIllegalArgumentException().isThrownBy(() -> new Fraction(1, 0)).withMessage("denominator is not allowed to be zero.");
    }

    @Test
    void compare_to_null() {
      assertThatNullPointerException().isThrownBy(() -> new Fraction(1, 2).compareTo(null)).withMessage("compareTo is not allowed to be null.");
    }
  }

  @Nested
  class Normalizing {

    @Test
    void normalize_0_x() {
      Fraction fraction = new Fraction(0, 6);

      assertThat(fraction.numerator()).isZero();
      assertThat(fraction.denominator()).isEqualTo(1);
    }

    @Test
    void normalize_improper_fraction() {
      Fraction unechterFraction = new Fraction(4, 6);
      assertThat(unechterFraction.numerator()).isEqualTo(2);
      assertThat(unechterFraction.denominator()).isEqualTo(3);
    }
  }

  @Nested
  class Multiplikation {

    @Test
    void multiply_multiplier_by_multiplicand() {
      Fraction multiplier = new Fraction(2, 3);
      Fraction multiplicand = new Fraction(4, 5);

      Fraction produkt = multiplier.multiply(multiplicand);

      assertThat(produkt).isEqualTo(new Fraction(8, 15));
    }
  }

  @Nested
  class Devide {
    @Test
    void devide_1_2_by_2_5() {
      Fraction dividend = new Fraction(1, 2);
      Fraction divisor = new Fraction(2, 5);

      Fraction quotient = dividend.divide(divisor);

      assertThat(quotient).isEqualTo(new Fraction(5, 4));
    }

    @Test
    void devide_the_same_fraction() {
      Fraction dividend = new Fraction(1, 2);
      Fraction divisor = new Fraction(1, 2);

      Fraction quotient = dividend.divide(divisor);

      assertThat(quotient).isEqualTo(new Fraction(1, 1));
    }
  }

  @Nested
  class Subtraction {

    @Test
    void subtract_first_minus_second() {
      Fraction minuend = new Fraction(2, 3);
      Fraction subtrahend = new Fraction(1, 5);

      Fraction difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualTo(new Fraction(7, 15));
    }

    @Test
    void subtract_with_same_denominator() {
      Fraction minuend = new Fraction(5, 9);
      Fraction subtrahend = new Fraction(2, 9);

      Fraction difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualTo(new Fraction(3, 9));
    }

    @Test
    void subtract_with_improper_faction_result() {
      Fraction minuend = new Fraction(21, 7);
      Fraction subtrahend = new Fraction(12, 7);

      Fraction difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualTo(new Fraction(9, 7));
    }
  }

  @Nested
  class Addition {
    @Test
    void chaining_addition_with_improper_fraction_result() {
      Fraction summand_1 = new Fraction(1, 2);
      Fraction summand_2 = new Fraction(1, 3);
      Fraction summand_3 = new Fraction(1, 4);

      Fraction sum = summand_1.add(summand_2).add(summand_3);

      assertThat(sum).isEqualTo(new Fraction(26, 24));
    }

    @Test
    void chaining_addition_with_proper_fraction_result() {
      Fraction summand_1 = new Fraction(1, 2);
      Fraction summand_2 = new Fraction(1, 3);
      Fraction summand_3 = new Fraction(1, 4);

      Fraction sum = summand_1.add(summand_2).add(summand_3);

      assertThat(sum).isEqualTo(new Fraction(13, 12));
    }

    @Test
    void addition_with_two_proper_fractions() {
      Fraction summand_1 = new Fraction(2, 3);
      Fraction summand_2 = new Fraction(1, 5);

      Fraction sum = summand_1.add(summand_2);

      assertThat(sum).isEqualTo(new Fraction(13, 15));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1() {
      Fraction summand_1 = new Fraction(1, 3);
      Fraction summand_2 = new Fraction(2, 3);

      Fraction sum = summand_1.add(summand_2);

      assertThat(sum).isEqualTo(new Fraction(3, 3));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1_reduced() {
      Fraction summand_1 = new Fraction(1, 3);
      Fraction summand_2 = new Fraction(2, 3);

      Fraction sum = summand_1.add(summand_2);

      assertThat(sum.numerator()).isEqualTo(1);
      assertThat(sum.denominator()).isEqualTo(1);
    }

  }


}

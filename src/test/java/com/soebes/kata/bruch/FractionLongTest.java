package com.soebes.kata.bruch;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class FractionLongTest {

  @Nested
  class Signum {
    @Test
    void signum_for_z_pos_n_pos() {
      FractionLong FractionLong = new FractionLong(1, 2);
      assertThat(FractionLong.signum()).isOne();
    }

    @Test
    void signum_for_z_neg_n_pos() {
      FractionLong FractionLong = new FractionLong(-1, 2);
      assertThat(FractionLong.signum()).isNegative();
    }

    @Test
    void signum_for_z_pos_n_neg() {
      FractionLong FractionLong = new FractionLong(1, -2);
      assertThat(FractionLong.signum()).isNegative();
    }

    @Test
    void signum_for_z_neg_n_neg() {
      FractionLong FractionLong = new FractionLong(-1, -2);
      assertThat(FractionLong.signum()).isPositive();
    }
  }

  @Nested
  class CompareTo {
    @Test
    void fraction_one_identical_to_fraction_two() {
      FractionLong FractionLong_eins = new FractionLong(1, 2);
      FractionLong FractionLong_zwei = new FractionLong(1, 2);

      assertThat(FractionLong_eins).isEqualByComparingTo(FractionLong_zwei);
    }

    @Test
    void fraction_one_greater_than_fraction_two() {
      FractionLong FractionLong_eins = new FractionLong(1, 1);
      FractionLong FractionLong_zwei = new FractionLong(1, 2);

      assertThat(FractionLong_eins.compareTo(FractionLong_zwei)).isPositive();
    }

    @Test
    void fraction_one_less_than_fraction_two() {
      FractionLong FractionLong_eins = new FractionLong(1, 3);
      FractionLong FractionLong_zwei = new FractionLong(1, 2);

      assertThat(FractionLong_eins.compareTo(FractionLong_zwei)).isNegative();
    }

  }

  @Nested
  class Verification {
    @Test
    void hash_code_and_equals() {
      EqualsVerifier.forClass(FractionLong.class).usingGetClass().verify();
    }

    @Test
    void check_to_string() {
      FractionLong FractionLong = new FractionLong(1, 2);
      assertThat(FractionLong).hasToString("FractionLong[numerator=1, denominator=2]");
    }
  }

  @Nested
  class InvalideValues {
    @Test
    void denominator_is_not_allowed_to_be_zero() {
      assertThatIllegalArgumentException().isThrownBy(() -> new FractionLong(1, 0)).withMessage("denominator is not allowed to be zero.");
    }

    @Test
    void compare_to_null() {
      assertThatNullPointerException().isThrownBy(() -> new FractionLong(1, 2).compareTo(null)).withMessage("compareTo is not allowed to be null.");
    }
  }

  @Nested
  class Normalizing {

    @Test
    void normalize_0_x() {
      FractionLong FractionLong = new FractionLong(0, 6);

      assertThat(FractionLong.numerator()).isZero();
      assertThat(FractionLong.denominator()).isEqualTo(1);
    }

    @Test
    void normalize_improper_fraction() {
      FractionLong unechterFractionLong = new FractionLong(4, 6);
      assertThat(unechterFractionLong.numerator()).isEqualTo(2);
      assertThat(unechterFractionLong.denominator()).isEqualTo(3);
    }
  }

  @Nested
  class Multiplikation {

    @Test
    void multiply_multiplier_by_multiplicand() {
      FractionLong multiplier = new FractionLong(2, 3);
      FractionLong multiplicand = new FractionLong(4, 5);

      FractionLong produkt = multiplier.multiply(multiplicand);

      assertThat(produkt).isEqualTo(new FractionLong(8, 15));
    }
  }

  @Nested
  class Devide {
    @Test
    void devide_1_2_by_2_5() {
      FractionLong dividend = new FractionLong(1, 2);
      FractionLong divisor = new FractionLong(2, 5);

      FractionLong quotient = dividend.divide(divisor);

      assertThat(quotient).isEqualTo(new FractionLong(5, 4));
    }

    @Test
    void devide_the_same_fraction() {
      FractionLong dividend = new FractionLong(1, 2);
      FractionLong divisor = new FractionLong(1, 2);

      FractionLong quotient = dividend.divide(divisor);

      assertThat(quotient).isEqualTo(new FractionLong(1, 1));
    }
  }

  @Nested
  class Subtraction {

    @Test
    void subtract_first_minus_second() {
      FractionLong minuend = new FractionLong(2, 3);
      FractionLong subtrahend = new FractionLong(1, 5);

      FractionLong difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualTo(new FractionLong(7, 15));
    }

    @Test
    void subtract_with_same_denominator() {
      FractionLong minuend = new FractionLong(5, 9);
      FractionLong subtrahend = new FractionLong(2, 9);

      FractionLong difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualTo(new FractionLong(3, 9));
    }

    @Test
    void subtract_with_improper_faction_result() {
      FractionLong minuend = new FractionLong(21, 7);
      FractionLong subtrahend = new FractionLong(12, 7);

      FractionLong difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualTo(new FractionLong(9, 7));
    }
  }

  @Nested
  class Addition {
    @Test
    void chaining_addition_with_improper_fraction_result() {
      FractionLong summand_1 = new FractionLong(1, 2);
      FractionLong summand_2 = new FractionLong(1, 3);
      FractionLong summand_3 = new FractionLong(1, 4);

      FractionLong sum = summand_1.add(summand_2).add(summand_3);

      assertThat(sum).isEqualTo(new FractionLong(26, 24));
    }

    @Test
    void chaining_addition_with_proper_fraction_result() {
      FractionLong summand_1 = new FractionLong(1, 2);
      FractionLong summand_2 = new FractionLong(1, 3);
      FractionLong summand_3 = new FractionLong(1, 4);

      FractionLong sum = summand_1.add(summand_2).add(summand_3);

      assertThat(sum).isEqualTo(new FractionLong(13, 12));
    }

    @Test
    void addition_with_two_proper_fractions() {
      FractionLong summand_1 = new FractionLong(2, 3);
      FractionLong summand_2 = new FractionLong(1, 5);

      FractionLong sum = summand_1.add(summand_2);

      assertThat(sum).isEqualTo(new FractionLong(13, 15));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1() {
      FractionLong summand_1 = new FractionLong(1, 3);
      FractionLong summand_2 = new FractionLong(2, 3);

      FractionLong sum = summand_1.add(summand_2);

      assertThat(sum).isEqualTo(new FractionLong(3, 3));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1_reduced() {
      FractionLong summand_1 = new FractionLong(1, 3);
      FractionLong summand_2 = new FractionLong(2, 3);

      FractionLong sum = summand_1.add(summand_2);

      assertThat(sum.numerator()).isEqualTo(1);
      assertThat(sum.denominator()).isEqualTo(1);
    }

  }

  @Nested
  class Limits {
    @Test
    void add_max_value() {
      FractionLong summand_1 = new FractionLong(Long.MAX_VALUE / 2, 1);
      FractionLong summand_2 = new FractionLong(Long.MAX_VALUE / 2, 1);

      FractionLong sum = summand_1.add(summand_2);

      assertThat(sum.numerator()).isEqualTo(Long.MAX_VALUE - 1);
      assertThat(sum.denominator()).isEqualTo(1);
    }

    @Test
    void add_min_value() {
      FractionLong summand_1 = new FractionLong(Long.MIN_VALUE / 2, 1);
      FractionLong summand_2 = new FractionLong(Long.MIN_VALUE / 2, 1);

      FractionLong sum = summand_1.add(summand_2);

      assertThat(sum.numerator()).isEqualTo(Long.MIN_VALUE);
      assertThat(sum.denominator()).isEqualTo(1);
    }
  }


}

package com.soebes.kata.fraction;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class FractionBigIntegerTest {

  private static final BigInteger THREE = BigInteger.valueOf(3);
  private static final BigInteger MINUS_ONE = BigInteger.ONE.negate();
  private static final BigInteger MINUS_TWO = BigInteger.TWO.negate();

  @Nested
  class BigDecimalValue {
    @Test
    void fraction_to_bigdecimal() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.ONE, BigInteger.ONE);
      assertThat(fraction.bigDecimalValue()).isEqualByComparingTo(BigDecimal.valueOf(1));
    }
  }

  @Nested
  class DoubleValue {
    @Test
    void fraction_to_double() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.ONE, BigInteger.ONE);
      assertThat(fraction.doubleValue()).isEqualTo(1.0, Offset.offset(1E-6));
    }
  }


  @Nested
  class Negate {
    @Test
    void fraction_negate() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.ONE, BigInteger.ONE);
      assertThat(fraction.negate()).isEqualByComparingTo(new FractionBigInteger(BigInteger.ONE.negate(), BigInteger.ONE));
    }

    @Test
    void fraction_negate_max() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ONE);
      assertThat(fraction.negate()).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(-Long.MAX_VALUE), BigInteger.ONE));
    }

    @Test
    @Disabled("Can not produce a exception.")
    void fraction_negate_min() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.ONE);
      assertThatExceptionOfType(ArithmeticException.class)
          .isThrownBy(() -> fraction.negate())
          .withMessage("long overflow");
    }
  }

  @Nested
  class Signum {
    @Test
    void signum_for_z_pos_n_pos() {
      FractionBigInteger fractionBigInteger = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);
      assertThat(fractionBigInteger.signum()).isOne();
    }

    @Test
    void signum_for_z_neg_n_pos() {
      FractionBigInteger fractionBigInteger = new FractionBigInteger(MINUS_ONE, BigInteger.TWO);
      assertThat(fractionBigInteger.signum()).isNegative();
    }

    @Test
    void signum_for_z_pos_n_neg() {
      FractionBigInteger fractionBigInteger = new FractionBigInteger(BigInteger.ONE, MINUS_TWO);
      assertThat(fractionBigInteger.signum()).isNegative();
    }

    @Test
    void signum_for_z_neg_n_neg() {
      FractionBigInteger fractionBigInteger = new FractionBigInteger(MINUS_ONE, MINUS_TWO);
      assertThat(fractionBigInteger.signum()).isPositive();
    }
  }

  @Nested
  class CompareTo {
    @Test
    void fraction_one_identical_to_fraction_two() {
      FractionBigInteger fractionLong_one = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);
      FractionBigInteger fractionLong_two = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);

      assertThat(fractionLong_one.compareTo(fractionLong_two)).isEqualTo(0);
    }

    @Test
    void fraction_one_greater_than_fraction_two() {
      FractionBigInteger fractionLong_one = new FractionBigInteger(BigInteger.ONE, BigInteger.ONE);
      FractionBigInteger fractionLong_two = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);

      assertThat(fractionLong_one.compareTo(fractionLong_two)).isPositive();
    }

    @Test
    void fraction_one_less_than_fraction_two() {
      FractionBigInteger fractionLong_one = new FractionBigInteger(BigInteger.ONE, THREE);
      FractionBigInteger fractionLong_two = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);

      assertThat(fractionLong_one.compareTo(fractionLong_two)).isNegative();
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
      FractionBigInteger fractionBigInteger = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);
      assertThat(fractionBigInteger).hasToString("FractionBigInteger[numerator=1, denominator=2]");
    }
  }

  @Nested
  class InvalideValues {
    @Test
    void denominator_is_not_allowed_to_be_zero() {
      assertThatIllegalArgumentException().isThrownBy(() -> new FractionBigInteger(BigInteger.ONE, BigInteger.ZERO)).withMessage("denominator is not allowed to be zero.");
    }

    @Test
    void compare_to_null() {
      assertThatNullPointerException().isThrownBy(() -> new FractionBigInteger(BigInteger.ONE, BigInteger.TWO).compareTo(null)).withMessage("compareTo is not allowed to be null.");
    }
  }

  @Nested
  class Normalizing {

    @Test
    void normalize_0_x() {
      FractionBigInteger fractionBigInteger = new FractionBigInteger(BigInteger.ZERO, BigInteger.valueOf(6));

      assertThat(fractionBigInteger.numerator()).isZero();
      assertThat(fractionBigInteger.denominator()).isEqualByComparingTo(BigInteger.ONE);
    }

    @Test
    void normalize_improper_fraction() {
      FractionBigInteger improperFraction = new FractionBigInteger(BigInteger.valueOf(4), BigInteger.valueOf(6));
      assertThat(improperFraction.numerator()).isEqualByComparingTo(BigInteger.TWO);
      assertThat(improperFraction.denominator()).isEqualTo(3);
    }
  }

  @Nested
  class Multiplikation {

    @Test
    void multiply_multiplier_by_multiplicand() {
      FractionBigInteger multiplier = new FractionBigInteger(BigInteger.TWO, THREE);
      FractionBigInteger multiplicand = new FractionBigInteger(BigInteger.valueOf(4), BigInteger.valueOf(5));

      FractionBigInteger produkt = multiplier.multiply(multiplicand);

      assertThat(produkt).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(8), BigInteger.valueOf(15)));
    }
  }

  @Nested
  class Divide {
    @Test
    void devide_1_2_by_2_5() {
      FractionBigInteger dividend = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);
      FractionBigInteger divisor = new FractionBigInteger(BigInteger.TWO, BigInteger.valueOf(5));

      FractionBigInteger quotient = dividend.divide(divisor);

      assertThat(quotient).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(5), BigInteger.valueOf(4)));
    }

    @Test
    void devide_the_same_fraction() {
      FractionBigInteger dividend = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);
      FractionBigInteger divisor = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);

      FractionBigInteger quotient = dividend.divide(divisor);

      assertThat(quotient).isEqualByComparingTo(new FractionBigInteger(BigInteger.ONE, BigInteger.ONE));
    }
  }

  @Nested
  class Subtraction {

    @Test
    void subtract_first_minus_second() {
      FractionBigInteger minuend = new FractionBigInteger(BigInteger.TWO, THREE);
      FractionBigInteger subtrahend = new FractionBigInteger(BigInteger.ONE, BigInteger.valueOf(5));

      FractionBigInteger difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(7), BigInteger.valueOf(15)));
    }

    @Test
    void subtract_with_same_denominator() {
      FractionBigInteger minuend = new FractionBigInteger(BigInteger.valueOf(5), BigInteger.valueOf(9));
      FractionBigInteger subtrahend = new FractionBigInteger(BigInteger.TWO, BigInteger.valueOf(9));

      FractionBigInteger difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualByComparingTo(new FractionBigInteger(THREE, BigInteger.valueOf(9)));
    }

    @Test
    void subtract_with_improper_faction_result() {
      FractionBigInteger minuend = new FractionBigInteger(BigInteger.valueOf(21), BigInteger.valueOf(7));
      FractionBigInteger subtrahend = new FractionBigInteger(BigInteger.valueOf(12), BigInteger.valueOf(7));

      FractionBigInteger difference = minuend.subtract(subtrahend);

      assertThat(difference).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(9), BigInteger.valueOf(7)));
    }
  }

  @Nested
  class Addition {
    @Test
    void chaining_addition_with_improper_fraction_result() {
      FractionBigInteger summand_1 = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);
      FractionBigInteger summand_2 = new FractionBigInteger(BigInteger.ONE, THREE);
      FractionBigInteger summand_3 = new FractionBigInteger(BigInteger.ONE, BigInteger.valueOf(4));

      FractionBigInteger sum = summand_1.add(summand_2).add(summand_3);

      assertThat(sum).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(26), BigInteger.valueOf(24)));
    }

    @Test
    void chaining_addition_with_proper_fraction_result() {
      FractionBigInteger summand_1 = new FractionBigInteger(BigInteger.ONE, BigInteger.TWO);
      FractionBigInteger summand_2 = new FractionBigInteger(BigInteger.ONE, THREE);
      FractionBigInteger summand_3 = new FractionBigInteger(BigInteger.ONE, BigInteger.valueOf(4));

      FractionBigInteger sum = summand_1.add(summand_2).add(summand_3);

      assertThat(sum).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(13), BigInteger.valueOf(12)));
    }

    @Test
    void addition_with_two_proper_fractions() {
      FractionBigInteger summand_1 = new FractionBigInteger(BigInteger.TWO, THREE);
      FractionBigInteger summand_2 = new FractionBigInteger(BigInteger.ONE, BigInteger.valueOf(5));

      FractionBigInteger sum = summand_1.add(summand_2);

      assertThat(sum).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(13), BigInteger.valueOf(15)));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1() {
      FractionBigInteger summand_1 = new FractionBigInteger(BigInteger.ONE, THREE);
      FractionBigInteger summand_2 = new FractionBigInteger(BigInteger.TWO, THREE);

      FractionBigInteger sum = summand_1.add(summand_2);

      assertThat(sum).isEqualByComparingTo(new FractionBigInteger(THREE, THREE));
    }

    @Test
    void add_1_3_plus_2_3_should_be_1_1_reduced() {
      FractionBigInteger summand_1 = new FractionBigInteger(BigInteger.ONE, THREE);
      FractionBigInteger summand_2 = new FractionBigInteger(BigInteger.TWO, THREE);

      FractionBigInteger sum = summand_1.add(summand_2);

      assertThat(sum.numerator()).isEqualByComparingTo(BigInteger.ONE);
      assertThat(sum.denominator()).isEqualByComparingTo(BigInteger.ONE);
    }

  }

  @Nested
  class Pow {

    @Test
    void power_to_two() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.TWO, THREE);

      FractionBigInteger produkt = fraction.pow(2);

      assertThat(produkt).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(4), BigInteger.valueOf(9)));
    }

    @Test
    void power_to_three() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.TWO, THREE);

      FractionBigInteger produkt = fraction.pow(3);

      assertThat(produkt).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(8), BigInteger.valueOf(27)));
    }

    @Test
    void power_to_ten() {
      FractionBigInteger fraction = new FractionBigInteger(BigInteger.TWO, THREE);

      FractionBigInteger produkt = fraction.pow(10);

      assertThat(produkt).isEqualByComparingTo(new FractionBigInteger(BigInteger.valueOf(1024), BigInteger.valueOf(59049)));
    }
  }

  @Nested
  class Limits {
    @Test
    void add_max_value() {
      FractionBigInteger summand_1 = new FractionBigInteger(BigInteger.valueOf(Long.MAX_VALUE).divide(BigInteger.TWO), BigInteger.ONE);
      FractionBigInteger summand_2 = new FractionBigInteger(BigInteger.valueOf(Long.MAX_VALUE).divide(BigInteger.TWO), BigInteger.ONE);

      FractionBigInteger sum = summand_1.add(summand_2);

      assertThat(sum.numerator()).isEqualTo(BigInteger.valueOf(Long.MAX_VALUE).subtract(BigInteger.ONE));
      assertThat(sum.denominator()).isEqualByComparingTo(BigInteger.ONE);
    }

    @Test
    void add_min_value() {
      FractionBigInteger summand_1 = new FractionBigInteger(BigInteger.valueOf(Long.MIN_VALUE).divide(BigInteger.TWO), BigInteger.ONE);
      FractionBigInteger summand_2 = new FractionBigInteger(BigInteger.valueOf(Long.MIN_VALUE).divide(BigInteger.TWO), BigInteger.ONE);

      FractionBigInteger sum = summand_1.add(summand_2);

      assertThat(sum.numerator()).isEqualByComparingTo(BigInteger.valueOf(Long.MIN_VALUE));
      assertThat(sum.denominator()).isEqualByComparingTo(BigInteger.ONE);
    }
  }


}

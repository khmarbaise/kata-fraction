package com.soebes.kata.fraction.ibased;

import com.soebes.kata.fraction.Operation;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

interface IFractionTest<T extends Operation<T>> {

  T createOne(); // 1/1

  T createOneAndAHalf();  // 1/2

  T createOneThird();  // 1/3

  T createOneQuarter();  // 1/4

  T expectedSum(); // 26/24

  @Test
  @DisplayName("Create the sum of three summands while chaining the plus class.")
  default void create_sum_of_three_summands_while_chaining_the_plus_calls() {
    T summand_1 = createOneAndAHalf();
    T summand_2 = createOneThird();
    T summand_3 = createOneQuarter();

    T sum = summand_1.plus(summand_2).plus(summand_3);

    assertThat(sum).isEqualTo(expectedSum());
  }

  @Test
  @DisplayName("fraction to double.")
  default void fraction_to_double() {
    assertThat(createOne().doubleValue()).isEqualTo(1.0, Offset.offset(1E-6));
  }

}

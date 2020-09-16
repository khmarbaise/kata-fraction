package com.soebes.kata.fraction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

interface IFractionTest<T extends Operation<T>> {

  T createSummand1();  // 1/2

  T createSummand2();  // 1/3

  T createSummand3();  // 1/4

  T expectedSum(); // 26/24

  @Test
  @DisplayName("Create the sum of three summands which chaining the plus class.")
  default void create_sum_of_three_summands_while_chaining_the_plus_calls() {
    T summand_1 = createSummand1();
    T summand_2 = createSummand2();
    T summand_3 = createSummand3();

    T sum = summand_1.plus(summand_2).plus(summand_3);

    assertThat(sum).isEqualTo(expectedSum());

  }
}

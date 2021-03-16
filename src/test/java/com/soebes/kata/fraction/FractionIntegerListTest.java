package com.soebes.kata.fraction;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

class FractionIntegerListTest {

  private static final List<FractionInteger> LIST_OF_FRACTION_INTEGERS = List.of(new FractionInteger(1, 2),
      new FractionInteger(2, 3),
      new FractionInteger(3, 4),
      new FractionInteger(4, 5));

  @Test
  void name() {
    assertThat(LIST_OF_FRACTION_INTEGERS).containsExactly(new FractionInteger(1, 2),
        new FractionInteger(2, 3),
        new FractionInteger(3, 4),
        new FractionInteger(4, 5));
  }

  @Test
  void list_test() {
    assertThat(LIST_OF_FRACTION_INTEGERS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(1);
      assertThat(fraction.denominator()).isEqualTo(2);
      assertThat(fraction).isEqualTo(new FractionInteger(1, 2));
    }, atIndex(0));
    assertThat(LIST_OF_FRACTION_INTEGERS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(2);
      assertThat(fraction.denominator()).isEqualTo(3);
      assertThat(fraction).isEqualTo(new FractionInteger(2, 3));
    }, atIndex(1));
    assertThat(LIST_OF_FRACTION_INTEGERS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(3);
      assertThat(fraction.denominator()).isEqualTo(4);
      assertThat(fraction).isEqualTo(new FractionInteger(3, 4));
    }, atIndex(2));
    assertThat(LIST_OF_FRACTION_INTEGERS).satisfies(fraction -> {
      assertThat(fraction.numerator()).isEqualTo(4);
      assertThat(fraction.denominator()).isEqualTo(5);
      assertThat(fraction).isEqualTo(new FractionInteger(4, 5));
    }, atIndex(3));
  }

}

package com.soebes.kata.parser;

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

import com.soebes.kata.fraction.FractionInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.of;

class ParseIntoFractionTest {

  static Stream<Arguments> fractionAsString() {
    return Stream.of(
        of("1/3", new FractionInteger(1, 3)),
        of("5/6", new FractionInteger(5, 6)),
        of("391/247", new FractionInteger(391, 247))
    );
  }

  @ParameterizedTest
  @MethodSource("fractionAsString")
  void convert_several_fractions_into_fraction_class(String fractionString, FractionInteger expectedFractionInteger) {
    FractionInteger fractionInteger = ParseIntoFraction.parse(fractionString);
    assertThat(fractionInteger).isEqualTo(expectedFractionInteger);
  }

  @Test
  void wrong_format_should_fail_with_illegal_argument_exception() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> ParseIntoFraction.parse("23"))
        .withMessage("The format is 'numerator/denominator' given: '23'");
  }
}

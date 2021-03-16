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
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(status = EXPERIMENTAL)
public final class ParseIntoFraction {

  private ParseIntoFraction() {
    // intentionally empty.
  }

  /**
   * A fraction which is given as a string like {@code 4/5}
   * will be converted into {@link FractionInteger}.
   *
   * @param fractionString The Fraction like {@code 4/5}
   * @return {@link FractionInteger}
   */
  public static FractionInteger parse(String fractionString) {
    String[] split = fractionString.split("/");
    if (split.length != 2) {
      throw new IllegalArgumentException(String.format("The format is 'numerator/denominator' given: '%s'", fractionString));
    }
    int numerator = Integer.parseInt(split[0]);
    int denominator = Integer.parseInt(split[1]);
    return new FractionInteger(numerator, denominator);
  }
}

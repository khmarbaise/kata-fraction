package com.soebes.kata.fraction.ibased;

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

import com.soebes.kata.fraction.FractionBigInteger;
import com.soebes.kata.fraction.FractionInteger;

import java.math.BigInteger;

/**
 * Test for {@link FractionInteger}.
 */
class FractionBigIntegerIFractionTest implements IFractionTest<FractionBigInteger> {

  public FractionBigInteger createOne() {
    return new FractionBigInteger(BigInteger.valueOf(1), BigInteger.valueOf(1));
  }

  public FractionBigInteger createOneAndAHalf() {
    return new FractionBigInteger(BigInteger.valueOf(1), BigInteger.valueOf(2));
  }

  public FractionBigInteger createOneThird() {
    return new FractionBigInteger(BigInteger.valueOf(1), BigInteger.valueOf(3));
  }

  public FractionBigInteger createOneQuarter() {
    return new FractionBigInteger(BigInteger.valueOf(1), BigInteger.valueOf(4));
  }

  public FractionBigInteger expectedSum() {
    return new FractionBigInteger(BigInteger.valueOf(26), BigInteger.valueOf(24));
  }

}

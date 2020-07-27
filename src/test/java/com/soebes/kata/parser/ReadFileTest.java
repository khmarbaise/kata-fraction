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

import com.soebes.kata.fraction.Fraction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

class ReadFileTest {


  static class XXX {

    private char LB = '{';
    private char RB = '}';

    void translateTo(int codePoint) {
      if (codePoint == LB) {
        System.out.println("Start");
      }

      if (codePoint == RB) {
        System.out.println("End");
      }
    }

  }

  enum Operators {
    Plus,
    Minus,
    Multiply,
    Divide,
    Power
  }

  // {1/2}+{1/3}-{5/6}
  // Fract+Fract-Fract
  //
  private static Function<Integer, Fraction> translator = s -> new Fraction(1,2);

  @Test
  void name() throws IOException {
    String pathToResource = this.getClass().getResource("/fractions.input").getPath();
    ReadFile.streamIntoCodePoints(Path.of(pathToResource))
//        .mapToObj()
        .forEach(s -> System.out.println("s = " + Integer.toHexString(s)));

    List<String> a = List.of("A", "B", "C", "D");
  }

}

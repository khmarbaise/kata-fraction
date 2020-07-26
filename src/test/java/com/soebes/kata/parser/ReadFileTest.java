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

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ReadFileTest {

  private static final Predicate<String> IS_COMMENT = s -> s.stripLeading().startsWith("#");

  private static final Predicate<String> IS_EMPTY_LINE = String::isBlank;

  private static final IntPredicate IS_WHITESPACE = Character::isWhitespace;

  /**
   * Read the file and filter out comment lines and empty lines.
   *
   * @param fileToRead The file to be read.
   * @return Stream with lines which do not contain comments and no empty lines.
   * @throws IOException in case of failure.
   */
  static Stream<String> readLinesWithoutComment(Path fileToRead) throws IOException {
    return Files.lines(fileToRead).filter(Predicate.not(IS_COMMENT).or(IS_EMPTY_LINE));
  }

  static IntStream streamIntoCodePoints(Path fileToRead) throws IOException {
    Stream<String> stringStream = readLinesWithoutComment(fileToRead);
    return stringStream
        .flatMapToInt(String::codePoints)
        .filter(IS_WHITESPACE.negate());
  }

  @Test
  void name() throws IOException {
    String pathToResource = this.getClass().getResource("/fractions.input").getPath();
    streamIntoCodePoints(Path.of(pathToResource))
        .forEach(s -> System.out.println("s = " + Integer.toHexString(s)));
  }

}

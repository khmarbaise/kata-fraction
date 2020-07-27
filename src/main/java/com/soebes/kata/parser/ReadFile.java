package com.soebes.kata.parser;

import org.apiguardian.api.API;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(status = EXPERIMENTAL)
public class ReadFile {
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
}
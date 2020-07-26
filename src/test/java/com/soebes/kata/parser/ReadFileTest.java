package com.soebes.kata.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ReadFileTest {

  /**
   * This will remove the leading white spaces and check if a line contain {@code #} as
   * a comment marker.
   */
  private static final Predicate<String> IS_COMMENT = s -> s.stripLeading().startsWith("#");
  /**
   * This will check if a line is empty.
   */
  private static final Predicate<String> IS_EMPTY_LINE = s -> s.isBlank();

  private static final IntPredicate IS_WHITESPACE = s -> Character.isWhitespace(s);

  /**
   * Read the file and filter out comment lines and empty lines.
   *
   * @param fileToRead The file to be read.
   * @return Stream with lines which do not contain comments and no empty lines.
   * @throws IOException
   */
  Stream<String> readLinesWithoutComment(Path fileToRead) throws IOException {
    return Files.lines(fileToRead).filter(Predicate.not(IS_COMMENT).or(IS_EMPTY_LINE));
  }

  IntStream streamIntoCodePoints(Path fileToRead) throws IOException {
    Stream<String> stringStream = readLinesWithoutComment(fileToRead);
    return stringStream
        .peek(s -> System.out.println("s = " + s))
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

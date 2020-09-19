package com.soebes.kata.regular;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

class RegularTest {

  private List<String> Items = List.of(
      "Release - 1",
      "Release - 2",
      "Experiment - First",
      "Experiment - Release",
      "Experiment - Second",
      "Datenbank"

  );
  @Test
  void name() {
    Stream<String> stringStream = Items.stream().filter(s -> s.matches("^Datenbank.*"));
    stringStream.forEach(System.out::println);

  }
}

package com.soebes.kata.fraction;

class XTest {

  void test(Object obj) {
    if (!(obj instanceof String)) {
      return;
    }
    System.out.println(((String) obj).trim());
  }
}

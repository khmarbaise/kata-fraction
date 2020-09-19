package com.soebes.kata.fraction;

public interface Operation<T> {

  T plus(T add);

  double doubleValue();
}

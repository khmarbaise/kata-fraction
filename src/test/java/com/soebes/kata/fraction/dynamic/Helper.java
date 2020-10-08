package com.soebes.kata.fraction.dynamic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

class Helper {

  static Constructor<?>[] getConstructors(Class<?> aClass) {
    Constructor<?>[] theConstructors = aClass.getConstructors();
    if (theConstructors.length != 1) {
      throw new IllegalArgumentException("More than one constructor defined.");
    }
    if (theConstructors[0].getParameterTypes().length != 2) {
      throw new IllegalArgumentException("More than two parameters in constructor.");
    }
    return theConstructors;
  }

  static Object newInstance(Constructor<?> constructor, Class<?> parameterType, long first, long second) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    return constructor.newInstance(convertToParameterType(parameterType, first), convertToParameterType(parameterType, second));
  }

  static Object convertToParameterType(Class<?> parameterType, Long value) {
    if (int.class.equals(parameterType)) {
      return value.intValue();
    } else if (long.class.equals(parameterType)) {
      return Long.valueOf(value);
    } else if (BigInteger.class.equals(parameterType)) {
      return BigInteger.valueOf(value);
    } else {
      throw new IllegalArgumentException("Unbekannter Typ");
    }
  }
}

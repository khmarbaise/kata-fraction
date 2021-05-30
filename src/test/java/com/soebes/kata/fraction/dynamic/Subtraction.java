package com.soebes.kata.fraction.dynamic;

import org.junit.jupiter.api.DynamicNode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.soebes.kata.fraction.dynamic.Helper.getConstructors;
import static com.soebes.kata.fraction.dynamic.Helper.newInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class Subtraction {

  static final Function<Class<?>, Stream<DynamicNode>> SUBTRACTION_TESTS = theClass -> Stream.of(
      dynamicTest("2/3 - 1/5 = 7/15", () -> subtract_2_3_from_1_5_should_be_7_15(theClass)),
      dynamicTest("5/9 - 2/9 = 3/9", () -> subtract_5_9_from_2_9_should_be_3_9(theClass)),
      dynamicTest("21/7 - 12/7 = 9/7", () -> subtract_21_7_from_12_7_should_be_9_7(theClass))
  );

  static void subtract_2_3_from_1_5_should_be_7_15(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    subctract_a_from_b_result_c(aClass, 2L, 3L, 1L, 5L, 7L, 15L);
  }

  static void subtract_5_9_from_2_9_should_be_3_9(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    subctract_a_from_b_result_c(aClass, 5L, 9L, 2L, 9L, 3L, 9L);
  }

  static void subtract_21_7_from_12_7_should_be_9_7(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    subctract_a_from_b_result_c(aClass, 21L, 7L, 12L, 7L, 9L, 7L);
  }

  private static void subctract_a_from_b_result_c(Class<?> aClass, long minuendNominator, long minuendDenominator, long subtrahendNominator, long subtrahendDenominator, long differenceNominator, long differenceDenominator) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<?>[] parameterTypes = getConstructors(aClass)[0].getParameterTypes();
    Class<?> parameterType = parameterTypes[0];

    Constructor<?> constructor = aClass.getConstructor(parameterTypes);

    Object minuend = newInstance(constructor, parameterType, minuendNominator, minuendDenominator);
    Object subtrahend = newInstance(constructor, parameterType, subtrahendNominator, subtrahendDenominator);

    Method plusMethod = minuend.getClass().getMethod("subtract", minuend.getClass());

    Object sum = plusMethod.invoke(minuend, subtrahend);

    Object difference = newInstance(constructor, parameterType, differenceNominator, differenceDenominator);
    assertThat(sum)
        .as("Difference %s to be equal to expected value %s", sum, difference)
        .isEqualTo(difference);
  }

}

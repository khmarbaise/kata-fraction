package com.soebes.kata.fraction.dynamic;

import org.junit.jupiter.api.DynamicNode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.soebes.kata.fraction.dynamic.Helper.convertToParameterType;
import static com.soebes.kata.fraction.dynamic.Helper.getConstructors;
import static com.soebes.kata.fraction.dynamic.Helper.newInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class Additions {

  static final Function<Class<?>, Stream<DynamicNode>> ADDITION_TESTS = theClass -> Stream.of(
      dynamicTest("1/3+2/3 = 1/1", () -> addition_1_3_plus_2_3_should_be_3_3(theClass)),
      dynamicTest("2/3+1/5 = 13/15", () -> addition_2_3_plus_1_5_should_be_13_15(theClass)),
      dynamicTest("1/2+1/3+1/4 = 13/12", () -> addition_1_2_and_1_3_and_1_4(theClass)),
      dynamicTest("1/3+2/3 = 1/1", () -> addition_1_3_plus_2_3_should_be_1_1_reduced(theClass))
  );

  static void addition_1_3_plus_2_3_should_be_3_3(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<?> theConstructor = getConstructors(aClass)[0];
    Class<?> parameterType = theConstructor.getParameterTypes()[0];

    Constructor<?> constructor = aClass.getConstructor(theConstructor.getParameterTypes());

    Object summand_1 = newInstance(constructor, parameterType, 1L, 3L);
    Object summand_2 = newInstance(constructor, parameterType, 2L, 3L);

    Method plusMethod = summand_1.getClass().getMethod("plus", summand_1.getClass());

    Object sum = plusMethod.invoke(summand_1, summand_2);

    Object expectedResult = newInstance(constructor, parameterType, 3L, 3L);
    assertThat(sum)
        .as("Sum %s to be equal to expected value %s", sum, expectedResult)
        .isEqualTo(expectedResult);
  }

  static void addition_2_3_plus_1_5_should_be_13_15(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<?> theConstructor = getConstructors(aClass)[0];
    Class<?> parameterType = theConstructor.getParameterTypes()[0];

    Constructor<?> constructor = aClass.getConstructor(theConstructor.getParameterTypes());

    Object summand_1 = newInstance(constructor, parameterType, 2L, 3L);
    Object summand_2 = newInstance(constructor, parameterType, 1L, 5L);

    Method plusMethod = summand_1.getClass().getMethod("plus", summand_1.getClass());

    Object sum = plusMethod.invoke(summand_1, summand_2);

    Object expectedResult = newInstance(constructor, parameterType, 13L, 15L);
    assertThat(sum)
        .as("Sum %s to be equal to expected value %s", sum, expectedResult)
        .isEqualTo(expectedResult);
  }

  static void addition_1_3_plus_2_3_should_be_1_1_reduced(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

    Class<?>[] parameterTypes = getConstructors(aClass)[0].getParameterTypes();
    Class<?> parameterType = parameterTypes[0];

    Constructor<?> constructor = aClass.getConstructor(parameterTypes);

    Object summand_1 = newInstance(constructor, parameterType, 1L, 3L);
    Object summand_2 = newInstance(constructor, parameterType, 2L, 3L);

    Method plusMethod = summand_1.getClass().getMethod("plus", summand_1.getClass());

    Object sum = plusMethod.invoke(summand_1, summand_2);

    Method numeratorMethod = sum.getClass().getMethod("numerator");
    Object numerator = numeratorMethod.invoke(sum);
    assertThat(numerator)
        .as("Sum %s to be equal to expected value %s", sum, numerator)
        .isEqualTo(convertToParameterType(parameterType, 1L));

    Method denominatorMethod = sum.getClass().getMethod("denominator");
    Object denominator = denominatorMethod.invoke(sum);
    assertThat(denominator)
        .as("Sum %s to be equal to expected value %s", sum, denominator)
        .isEqualTo(convertToParameterType(parameterType, 1L));


  }

  static void addition_1_2_and_1_3_and_1_4(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<?>[] theConstructors = getConstructors(aClass);

    Class<?> parameterType = theConstructors[0].getParameterTypes()[0];

    Constructor<?> constructors = aClass.getConstructor(theConstructors[0].getParameterTypes());

    Object summand_1 = newInstance(constructors, parameterType, 1L, 2L);
    Object summand_2 = newInstance(constructors, parameterType, 1L, 3L);
    Object summand_3 = newInstance(constructors, parameterType, 1L, 4L);

    Method plusMethodFirst = summand_1.getClass().getMethod("plus", summand_1.getClass());

    Object sum1 = plusMethodFirst.invoke(summand_1, summand_2);

    Method plusMethodSecond = sum1.getClass().getMethod("plus", summand_1.getClass());

    Object sum = plusMethodSecond.invoke(sum1, summand_3);

    Object expectedResult = newInstance(constructors, parameterType, 13L, 12L);
    assertThat(sum)
        .as("Sum %s to be equal to expected value %s", sum, expectedResult)
        .isEqualTo(expectedResult);
  }

}

package com.soebes.kata.fraction.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.commons.support.ReflectionSupport;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class DynamicFractionTest {

    private static final Predicate<Class<?>> isNotInterfaceAndNotMember = s -> !s.isInterface()
            && !s.isMemberClass()
            && s.getSimpleName().startsWith("Fraction");
    private static final Predicate<String> isNoTest = s -> !s.endsWith("Test");

    private static final Function<Class<?>, Stream<DynamicNode>> ADDITION_TESTS = theClass -> Stream.of(
            dynamicTest("1/3+2/3 = 1/1", () -> addition_1_3_plus_2_3(theClass)),
            dynamicTest("2/3+1/5 = 13/15", () -> addition_2_3_plus_1_5(theClass)),
            dynamicTest("1/2+1/3+1/4 = 13/12", () -> addition_1_2_and_1_3_and_1_4(theClass))
    );

    @TestFactory
    @DisplayName("Classes")
    Stream<DynamicNode> allClassesTest() {
        List<Class<?>> allClassesInPackage = ReflectionSupport
                .findAllClassesInPackage("com.soebes.kata.fraction", isNotInterfaceAndNotMember, isNoTest);

        return allClassesInPackage.stream()
                .map(theClass ->
                        dynamicContainer(theClass.getSimpleName(), Stream.of(
                                dynamicContainer("Addition", ADDITION_TESTS.apply(theClass))
                            )
                        )
                );
    }

    private static void addition_1_3_plus_2_3(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] theConstructors = getConstructors(aClass);

        Class<?> parameterType = theConstructors[0].getParameterTypes()[0];

        Constructor<?> constructors = aClass.getConstructor(theConstructors[0].getParameterTypes());

        Object summand_1 = newInstance(constructors, parameterType, 1L, 3L);
        Object summand_2 = newInstance(constructors, parameterType, 2L, 3L);

        Method plusMethod = summand_1.getClass().getMethod("plus", summand_1.getClass());

        Object sum = plusMethod.invoke(summand_1, summand_2);

        Object expectedResult = newInstance(constructors, parameterType, 1L, 1L);
        assertThat(sum)
                .as("Sum %s to be equal to expected value %s", sum, expectedResult)
                .isEqualTo(expectedResult);
    }

    private static void addition_2_3_plus_1_5(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] theConstructors = getConstructors(aClass);

        Class<?> parameterType = theConstructors[0].getParameterTypes()[0];

        Constructor<?> constructors = aClass.getConstructor(theConstructors[0].getParameterTypes());

        Object summand_1 = newInstance(constructors, parameterType, 2L, 3L);
        Object summand_2 = newInstance(constructors, parameterType, 1L, 5L);

        Method plusMethod = summand_1.getClass().getMethod("plus", summand_1.getClass());

        Object sum = plusMethod.invoke(summand_1, summand_2);

        Object expectedResult = newInstance(constructors, parameterType, 13L, 15L);
        assertThat(sum)
                .as("Sum %s to be equal to expected value %s", sum, expectedResult)
                .isEqualTo(expectedResult);
    }

    private static void addition_1_2_and_1_3_and_1_4(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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

    private static Constructor<?>[] getConstructors(Class<?> aClass) {
        Constructor<?>[] theConstructors = aClass.getConstructors();
        if (theConstructors.length != 1) {
            throw new IllegalArgumentException("More than one constructor defined.");
        }
        if (theConstructors[0].getParameterTypes().length != 2) {
            throw new IllegalArgumentException("More than two parameters in constructor.");
        }
        return theConstructors;
    }

    private static Object newInstance(Constructor<?> constructor, Class<?> parameterType, long first, long second) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return constructor.newInstance(convertToParameterType(parameterType, first), convertToParameterType(parameterType, second));
    }

    private static Object convertToParameterType(Class<?> parameterType, Long value) {
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


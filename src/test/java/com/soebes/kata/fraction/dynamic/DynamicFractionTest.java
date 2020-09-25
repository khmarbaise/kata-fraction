package com.soebes.kata.fraction.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.commons.support.ReflectionSupport;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;
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

    @TestFactory
    @DisplayName("Classes")
    Stream<DynamicNode> allClassesTest() {
        List<Class<?>> allClassesInPackage = ReflectionSupport
                .findAllClassesInPackage("com.soebes.kata.fraction", isNotInterfaceAndNotMember, isNoTest);

        return allClassesInPackage.stream()
                .map(theClass ->
                                dynamicContainer(theClass.getSimpleName(), Stream.of(
//                            dynamicTest("isNotNullTest-" + theClass.getSimpleName(), isNotNullTest(theClass)),
//                            dynamicTest("T2-" + theClass.getSimpleName(), () -> assertThat(theClass.isInterface()).isFalse()),
                                        dynamicContainer("Addition", Stream.of(
                                                dynamicTest("1/3+2/3 = 1/1 plus()", () -> addition_1_3_plus_2_3(theClass)))
                                        )

                                        )
                                )
                );
    }

    private Executable isNotNullTest(Class<?> aClass) {
        return () -> assertThat(aClass.getSimpleName()).isNotNull();
    }

    private void addition_1_3_plus_2_3(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] theConstructors = aClass.getConstructors();
        if (theConstructors.length != 1) {
            throw new IllegalArgumentException("Mehr als ein CTor definiert.");
        }

        Class<?> parameterType = theConstructors[0].getParameterTypes()[0];

        Constructor<?> constructors = aClass.getConstructor(parameterType);

        Object summand_1 = constructors.newInstance(one(parameterType), three(parameterType));
        Object summand_2 = constructors.newInstance(two(parameterType), three(parameterType));

        Method plusMethod = summand_1.getClass().getMethod("plus", summand_1.getClass());

        Object sum = plusMethod.invoke(summand_1, summand_2);

        Object expectedResult = constructors.newInstance(one(parameterType), one(parameterType));
        assertThat(sum)
                .as("Sum %s to be equal to expected value %s", sum, expectedResult)
                .isEqualTo(expectedResult);
    }

    Object one(Class<?> parameterType) {
        return convertToParameterType(parameterType, 1L);
    }

    Object two(Class<?> parameterType) {
        return convertToParameterType(parameterType, 2L);
    }

    Object three(Class<?> parameterType) {
        return convertToParameterType(parameterType, 3L);
    }

    Object convertToParameterType(Class<?> parameterType, Long value) {
        if (int.class.equals(parameterType)) {
            return value.intValue();
        } else if (long.class.equals(parameterType)) {
            return Long.valueOf(value);
        } else if (BigInteger.class.equals(parameterType)) {
            return BigInteger.valueOf(value);
        } else {
            throw new IllegalArgumentException("Unbekannter Typ");
        }
        /*
                return switch (parameterType) {
            case int.class -> value.intValue();
            case long.class -> Long.valueOf(value);
            case BigInteger.class -> BigInteger.valueOf(value);
            default -> throw new IllegalArgumentException("");
        };

         */
    }


//    @TestFactory
//    Stream<DynamicNode> dynamicTestsWithContainers() {
//        return Stream.of("A", "B", "C")
//                .map(input -> dynamicContainer("Container " + input, Stream.of(
//                        dynamicTest("not null", () -> assertNotNull(input)),
//                        dynamicContainer("properties", Stream.of(
//                                dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
//                                dynamicTest("not empty", () -> checkForValue(input))
//                        ))
//                )));
//    }

}


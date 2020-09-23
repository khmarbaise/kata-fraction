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

@DisplayName("A dynamic test")
class DynamicFractionTest {

    private static final Predicate<Class<?>> isNotInterfaceAndNotMember = s -> !s.isInterface()
            && !s.isMemberClass()
            && s.getSimpleName().equals("Fraction");
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
                            dynamicTest("1/3+2/3 = 3/3 plus()", () -> addition_1_3_plus_2_3(theClass))
                        )
                    )
                );
    }

    private Executable isNotNullTest(Class<?> aClass) {
        return () -> assertThat(aClass.getSimpleName()).isNotNull();
    }

    private Executable addition_1_3_plus_2_3(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] theConstructors = aClass.getConstructors();
        if (theConstructors.length != 1) {
            throw new IllegalArgumentException("Mehr als ein CTor definiert.");
        }

        Class<?>[] parameterTypes = theConstructors[0].getParameterTypes();

        Constructor<?> constructors = aClass.getConstructor(parameterTypes);

        Object summand_1 = constructors.newInstance(convertToParameterType(parameterTypes[0], 1L), convertToParameterType(parameterTypes[0], 3L));
        Object summand_2 = constructors.newInstance(convertToParameterType(parameterTypes[0], 2L), convertToParameterType(parameterTypes[0], 3L));

        Method plusMethod = summand_1.getClass().getMethod("plus", summand_1.getClass());

        Object sum = plusMethod.invoke(summand_1, summand_2);

        Object expectedResult = constructors.newInstance(convertToParameterType(parameterTypes[0], 1L), convertToParameterType(parameterTypes[0], 2L));
        assertThat(sum).isEqualTo(expectedResult);
        return () -> {};
    }

    Object convertToParameterType(Class<?> parameterType, Long value) {
        if (int.class.equals(parameterType)) {
            return Integer.valueOf(value.intValue());
        } else if (long.class.equals(parameterType)) {
            return Long.valueOf(value);
        } else if (BigInteger.class.equals(parameterType)) {
            return BigInteger.valueOf(value);
        } else {
            throw new IllegalArgumentException("Unbekannter Typ");
        }
    }


}


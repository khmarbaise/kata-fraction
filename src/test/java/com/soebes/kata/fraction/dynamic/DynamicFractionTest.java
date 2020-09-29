package com.soebes.kata.fraction.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.commons.support.ReflectionSupport;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.soebes.kata.fraction.dynamic.Additions.*;
import static com.soebes.kata.fraction.dynamic.Subtraction.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class DynamicFractionTest {

    private static final Predicate<Class<?>> isNotInterfaceAndNotMember = s -> !s.isInterface()
            && !s.isMemberClass()
            && s.getSimpleName().startsWith("Fraction");

    private static final Predicate<String> isNoTest = s -> !s.endsWith("Test");

    private static final Function<Class<?>, Stream<DynamicNode>> ADDITION_TESTS = theClass -> Stream.of(
            dynamicTest("1/3+2/3 = 1/1", () -> addition_1_3_plus_2_3(theClass)),
            dynamicTest("2/3+1/5 = 13/15", () -> addition_2_3_plus_1_5_should_be_13_15(theClass)),
            dynamicTest("1/2+1/3+1/4 = 13/12", () -> addition_1_2_and_1_3_and_1_4(theClass)),
            dynamicTest("1/3+2/3 = 1/1", () -> addition_1_3_plus_2_3_should_be_1_1_reduced(theClass))
    );

    private static final Function<Class<?>, Stream<DynamicNode>> SUBRACTION_TESTS = theClass ->  Stream.of(
            dynamicTest("2/3 - 1/5 = 7/15", () -> subtract_2_3_from_1_5_should_be_7_15(theClass)),
            dynamicTest("5/9 - 2/9 = 3/9", () -> subtract_5_9_from_2_9_should_be_3_9(theClass)),
            dynamicTest("21/7 - 12/7 = 9/7", () -> subtract_21_7_from_12_7_should_be_9_7(theClass))
    );

    @TestFactory
    @DisplayName("Classes")
    Stream<DynamicNode> allClassesTest() {
        List<Class<?>> allClassesInPackage = ReflectionSupport
                .findAllClassesInPackage("com.soebes.kata.fraction", isNotInterfaceAndNotMember, isNoTest);

        return allClassesInPackage.stream()
                .map(theClass ->
                        dynamicContainer(theClass.getSimpleName(), Stream.of(
                                dynamicContainer("Addition", ADDITION_TESTS.apply(theClass)),
                                dynamicContainer("Subtraction", SUBRACTION_TESTS.apply(theClass))

                                )
                        )
                );
    }


}


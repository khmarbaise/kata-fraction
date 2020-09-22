package com.soebes.kata.fraction.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.commons.support.ReflectionSupport;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("A dynamic test")
class DynamicFractionTest {

    private static final Predicate<Class<?>> isNotInterfaceAndNotMember = s -> !s.isInterface() && !s.isMemberClass();
    private static final Predicate<String> isNoTest = s -> !s.endsWith("Test");

    @Test
    void name() {
        List<Class<?>> allClassesInPackage = ReflectionSupport
                .findAllClassesInPackage("com.soebes.kata.fraction", isNotInterfaceAndNotMember, isNoTest);
        allClassesInPackage.forEach(getXXXX());
    }

    private Consumer<Class<?>> getXXXX() {
        return aClass -> {
            System.out.println("aClass = " + aClass.getName() + " " + aClass.getNestMembers() + " Host:" + aClass.getNestHost() + " X:" + aClass.isNestmateOf(aClass));
            Class<?>[] nestMembers = aClass.getNestMembers();
            for (int i = 0; i < nestMembers.length; i++) {
                System.out.println("nestMembers[" + i + "]= " + nestMembers[i].getName());
            }
        };
    }

    @TestFactory
    @DisplayName("First tests")
    Stream<DynamicNode> dynamicTestsWithContainers() {
        return Stream.of("A", "B", "C")
                .map(input -> dynamicContainer("Container " + input, Stream.of(
                        dynamicTest("not null", () -> assertThat(input).isNotNull()),
                        dynamicContainer("properties", Stream.of(
                                dynamicTest("length > 0", () -> assertThat(input).isNotNull()),
                                dynamicTest("not empty", () -> assertThat(input).isEmpty())
                        ))
                )));
    }


}


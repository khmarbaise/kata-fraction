package com.soebes.kata.fraction.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.commons.support.ReflectionSupport;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

class DynamicFractionTest {

  private static final Predicate<Class<?>> isPublic = s -> Modifier.isPublic(s.getModifiers());
  private static final Predicate<Class<?>> isInterface = Class::isInterface;
  private static final Predicate<Class<?>> isMember = Class::isMemberClass;

  private static final Predicate<Class<?>> isNotInterfaceAndNotMember = not(isInterface).and(isPublic).and(not(isMember));

  private static final Predicate<String> isNoTest = s -> !s.endsWith("Test");

  @TestFactory
  @DisplayName("Classes")
  Stream<DynamicNode> allClassesTest() {
    List<Class<?>> allClassesInPackage = ReflectionSupport
        .findAllClassesInPackage("com.soebes.kata.fraction", isNotInterfaceAndNotMember, isNoTest);

    return allClassesInPackage.stream()
        .map(theClass ->
            dynamicContainer(theClass.getSimpleName(), Stream.of(
                dynamicContainer("Addition", Additions.ADDITION_TESTS.apply(theClass)),
                dynamicContainer("Subtraction", Subtraction.SUBTRACTION_TESTS.apply(theClass))
                )
            )
        );
  }


}


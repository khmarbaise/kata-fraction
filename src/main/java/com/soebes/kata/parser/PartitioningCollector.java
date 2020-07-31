package com.soebes.kata.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.Collections.emptySet;

class PartitioningCollector<T> implements Collector<T, List<List<T>>, List<List<T>>> {

  private final List<T> batch;

  public PartitioningCollector() {
    this.batch = new ArrayList<>();
  }

  @Override
  public Supplier<List<List<T>>> supplier() {
    System.out.println("PartitioningCollector.supplier");
    return LinkedList::new;
  }

  @Override
  public BiConsumer<List<List<T>>, T> accumulator() {
    System.out.println("PartitioningCollector.accumulator");
    return (total, element) -> {
      batch.add(element);
      System.out.println("element = " + element + " total = " + total);
      if (element.equals("}")) {
        total.add(new ArrayList<>(batch));
        batch.clear();
      }
    };
  }

  @Override
  public BinaryOperator<List<List<T>>> combiner() {
    System.out.println("PartitioningCollector.combiner");
    return (left, right) -> {
      List<List<T>> result = new ArrayList<>();
      result.addAll(left);
      result.addAll(left);
      return result;
    };
  }

  @Override
  public Function<List<List<T>>, List<List<T>>> finisher() {
    System.out.println("PartitioningCollector.finisher");
    return result -> {
      if (!batch.isEmpty()) {
        result.add(new ArrayList<>(batch));
        batch.clear();
      }
      return result;
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    System.out.println("PartitioningCollector.characteristics");
    return emptySet();
  }
}
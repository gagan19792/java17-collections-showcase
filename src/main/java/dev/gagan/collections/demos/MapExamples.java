package dev.gagan.collections.demos;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import dev.gagan.collections.fixtures.DataFixtures;
import dev.gagan.collections.model.Order;

public class MapExamples {

  public static void main(String[] args) {
    var orders = DataFixtures.orders();

    // Group orders by customer tier (immutable result via toUnmodifiableMap)
    var byTier =
        orders.stream()
            .collect(
                Collectors.groupingBy(
                    o -> o.customer().tier(),
                    Collectors.mapping(Order::id, Collectors.toUnmodifiableList())));
    System.out.println(byTier);

    // LinkedHashMap LRU (access‑order) for tiny cache
    var cache =
        new LinkedHashMap<String, Order>(16, 0.75f, true) {
          protected boolean removeEldestEntry(Map.Entry<String, Order> e) {
            return size() > 5;
          }
        };
    orders.forEach(o -> cache.put(o.id(), o));
    System.out.println("LRU cache keys: " + cache.keySet());
    cache.get("O-101"); // touch to change access order
    System.out.println("LRU cache keys: " + cache.keySet());
    orders.forEach(o -> cache.put(o.id() + "-dup", o)); // trigger evictions
    System.out.println("LRU cache keys: " + cache.keySet());
  }
}

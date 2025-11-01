package dev.gagan.collections.demos;

import java.util.ArrayList;
import java.util.List;

import dev.gagan.collections.fixtures.DataFixtures;

public class ListExamples {

  public static void main(String[] args) {
    var orders = new ArrayList<>(DataFixtures.orders());

    // stable iteration order (ArrayList), filter/mutate
    orders.removeIf(o -> o.items().isEmpty());

    // CopyOnWrite for read‑mostly scenarios (demo only)
    var threadSafe = new java.util.concurrent.CopyOnWriteArrayList<>(orders);
    threadSafe.addAll(orders);

    System.out.println("Total orders: " + orders.size());
    System.out.println("Thread‑safe snapshot size: " + threadSafe.size());

    // Convert to immutable view
    var immutable = List.copyOf(orders);
    System.out.println("First order id: " + immutable.get(0).id());
  }
}

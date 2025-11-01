package dev.gagan.collections.demos;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

public class ConcurrentExamples {

  public static void main(String[] args) throws InterruptedException {
    Map<String, Integer> hits = new ConcurrentHashMap<>();
    var pool = Executors.newFixedThreadPool(4);

    for (int i = 0; i < 1_000; i++) {
      int id = i % 10;
      pool.submit(() -> hits.merge("K" + id, 1, Integer::sum));
    }
    pool.shutdown();
    pool.awaitTermination(2, java.util.concurrent.TimeUnit.SECONDS);
    System.out.println("Hits: " + hits);

    // Read‑mostly list
    var list = new CopyOnWriteArrayList<>(List.of("a", "b", "c"));
    list.add("d");
    System.out.println("COW list: " + list);
  }
}

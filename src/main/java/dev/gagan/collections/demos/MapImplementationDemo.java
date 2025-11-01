package dev.gagan.collections.demos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.IntStream;

public class MapImplementationDemo {

  enum State {
    NEW,
    RUNNING,
    DONE
  }

  public static void main(String[] args) throws InterruptedException, IOException {
    hashMap();
    linkedHashMap();
    treeMap();
    identityHashMap();
    weakHashMap();
    enumMap();
    properties();
    concurrentHashMap();
    concurrentSkipListMap();
  }

  private static void concurrentSkipListMap() {
    ConcurrentNavigableMap<Integer, String> cslm = new ConcurrentSkipListMap<>();
    cslm.put(2, "B");
    cslm.put(1, "A");
    cslm.put(3, "C");
    System.out.println("ConcurrentSkipListMap headMap(2,true): " + cslm.headMap(2, true));
  }

  private static void concurrentHashMap() {
    ConcurrentMap<String, Long> chm = new ConcurrentHashMap<>();
    IntStream.range(0, 1000).parallel().forEach(i -> chm.merge("hits", 1L, Long::sum));
    System.out.println("ConcurrentHashMap hits: " + chm.get("hits"));
  }

  private static void properties() throws IOException {
    Properties props = new Properties();
    props.setProperty("env", "dev");
    try (var out = Files.newOutputStream(Path.of("app.properties"))) {
      props.store(out, "demo");
    }
    System.out.println("Properties saved: app.properties");
  }

  private static void enumMap() {
    EnumMap<State, Integer> em = new EnumMap<>(State.class);
    em.merge(State.NEW, 1, Integer::sum);
    em.merge(State.DONE, 1, Integer::sum);
    System.out.println("EnumMap: " + em);
  }

  private static void weakHashMap() throws InterruptedException {
    Map<Object, String> weak = new WeakHashMap<>();
    Object key = new Object();
    weak.put(key, "temp");
    key = null;
    System.gc();
    Thread.sleep(50);
    System.out.println("WeakHashMap size (eventually 0): " + weak.size());
  }

  private static void identityHashMap() {
    Map<String, Integer> id = new IdentityHashMap<>();
    id.put(new String("A"), 1);
    id.put(new String("A"), 2);
    System.out.println("IdentityHashMap size: " + id.size());
  }

  private static void treeMap() {
    NavigableMap<Integer, String> tm = new TreeMap<>();
    tm.put(10, "A");
    tm.put(3, "B");
    tm.put(7, "C");
    tm.put(2, "D");
    tm.put(11, "E");
    System.out.println("TreeMap subMap[3,10): " + tm.subMap(3, true, 10, false));
  }

  private static void linkedHashMap() {
    Map<String, String> lru =
        new LinkedHashMap<>(16, 0.75f, true) {
          protected boolean removeEldestEntry(Map.Entry<String, String> e) {
            return size() > 2;
          }
        };
    lru.put("a", "1");
    lru.put("b", "2");
    lru.put("c", "3");
    lru.get("a");
    lru.put("d", "4");
    System.out.println("LinkedHashMap LRU keys: " + lru.keySet());
  }

  private static void hashMap() {
    Map<String, Integer> hm = new HashMap<>();
    hm.put("a", 1);
    // In below line key "a" already present in Map, so it will add provided value to existing
    // value(1+1).
    hm.merge("a", 1, Integer::sum);
    System.out.println("HashMap: " + hm);
    // In below line key "b" already present in Map, so  it will add into the map.
    hm.merge("b", 1, Integer::sum);
    System.out.println("HashMap: " + hm);
  }
}

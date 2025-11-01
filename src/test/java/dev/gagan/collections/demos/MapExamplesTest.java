package dev.gagan.collections.demos;

import static org.junit.Assert.assertFalse;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class MapExamplesTest {

  @Test
  public void lru() {
    var cache =
        new LinkedHashMap<String, String>(16, 0.75f, true) {
          protected boolean removeEldestEntry(Map.Entry<String, String> e) {
            return size() > 2;
          }
        };
    cache.put("a", "1");
    cache.put("b", "2");
    cache.put("c", "3");
    cache.get("a"); // a is most‑recent
    cache.put("d", "4");
    assertFalse(cache.containsKey("b")); // b evicted first
  }
}

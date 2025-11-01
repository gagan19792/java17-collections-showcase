package dev.gagan.collections.demos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.List;

import org.junit.Test;

public class AdvancedMapExamplesTest {

  @Test
  public void identityHashMapUsesReferenceEquality() {
    var id = new IdentityHashMap<String, Integer>();
    id.put(new String("A"), 1);
    id.put(new String("A"), 2);
    assertEquals(2, id.size());
  }

  @Test
  public void enumMapIsOrderedByEnum() {
    enum S {
      A,
      B,
      C
    }
    var em = new EnumMap<S, Integer>(S.class);
    em.put(S.B, 2);
    em.put(S.A, 1);
    em.put(S.C, 3);
    assertEquals(List.of(S.A, S.B, S.C), new ArrayList<>(em.keySet()));
  }
}

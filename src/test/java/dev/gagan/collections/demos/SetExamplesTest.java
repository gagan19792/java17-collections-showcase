package dev.gagan.collections.demos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dev.gagan.collections.fixtures.DataFixtures;

public class SetExamplesTest {

  @Test
  public void uniqueness() {
    var set = new java.util.HashSet<>(DataFixtures.products());
    set.addAll(DataFixtures.products());
    assertEquals(DataFixtures.products().size(), set.size());
  }
}

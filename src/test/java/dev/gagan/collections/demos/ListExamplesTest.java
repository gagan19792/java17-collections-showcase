package dev.gagan.collections.demos;

import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.Test;

import dev.gagan.collections.fixtures.DataFixtures;

public class ListExamplesTest {

  @Test
  public void immutableList() {
    var orders = List.copyOf(DataFixtures.orders());
    assertThrows(UnsupportedOperationException.class, () -> orders.add(null));
  }
}

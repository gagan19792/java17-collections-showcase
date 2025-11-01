package dev.gagan.collections.demos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dev.gagan.collections.fixtures.DataFixtures;
import dev.gagan.collections.model.Product;

public class StreamExamplesTest {

  @Test
  public void distinctSkus() {
    var skus =
        DataFixtures.orders().stream()
            .flatMap(o -> o.items().stream())
            .map(Product::sku)
            .distinct()
            .sorted()
            .toList();
    assertTrue(skus.size() >= 3);
  }
}

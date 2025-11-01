package dev.gagan.collections.demos;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;

import dev.gagan.collections.fixtures.DataFixtures;
import dev.gagan.collections.model.Product;

public class SetExamples {

  public static void main(String[] args) {
    var products = new HashSet<>(DataFixtures.products());

    // Adding duplicates shows uniqueness
    products.addAll(DataFixtures.products());
    System.out.println("Unique products: " + products.size());

    var ordered = new LinkedHashSet<>(products); // deterministic order
    var sorted = new java.util.TreeSet<>(Comparator.comparing(Product::sku));
    sorted.addAll(products);
    System.out.println("First sorted SKU: " + sorted.iterator().next().sku());

    // EnumSet demo
    var paymentKinds =
        EnumSet.of(
            dev.gagan.collections.model.PaymentTypes.CARD,
            dev.gagan.collections.model.PaymentTypes.WALLET);
    System.out.println("Payment kinds: " + paymentKinds);
  }
}

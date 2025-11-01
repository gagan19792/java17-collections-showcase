package dev.gagan.collections.demos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dev.gagan.collections.fixtures.DataFixtures;
import dev.gagan.collections.model.CardPayment;
import dev.gagan.collections.model.Product;

public class StreamExamples {
  public static void main(String[] args) {
    var orders = DataFixtures.orders();
    // Stream.toList() available in 16+, returns unmodifiable list
    List<String> skus =
        orders.stream()
            .flatMap(o -> o.items().stream())
            .map(Product::sku)
            .distinct()
            .sorted()
            .toList();
    System.out.println("Unique SKUs: " + skus);

    // Pattern matching for instanceof (final since 16)
    var payments = DataFixtures.payments();
    long cardCount =
        payments.stream()
            .filter(p -> p instanceof CardPayment cp && cp.network().equals("VISA"))
            .count();
    System.out.println("VISA payments: " + cardCount);

    // Summaries by customer
    Map<String, BigDecimal> spendByCustomer =
        orders.stream()
            .collect(
                Collectors.groupingBy(
                    o -> o.customer().name(),
                    Collectors.reducing(
                        BigDecimal.ZERO,
                        o ->
                            o.items().stream()
                                .map(Product::price)
                                .reduce(BigDecimal.ZERO, BigDecimal::add),
                        BigDecimal::add)));
    System.out.println(spendByCustomer);
  }
}

package dev.gagan.collections.fixtures;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import dev.gagan.collections.model.*;

public class DataFixtures {
  private DataFixtures() {}

  public static List<Customer> customers() {
    return List.of(
        new Customer("C001", "Aditi", "aditi@example.com", "GOLD"),
        new Customer("C002", "Brian", "brian@example.com", "SILVER"),
        new Customer("C003", "Chinmay", "chinmay@example.com", "GOLD"),
        new Customer("C004", "Daria", "daria@example.com", "BRONZE"));
  }

  public static List<Product> products() {
    return List.of(
        new Product("P-TSHIRT", "APPAREL", "Basic Tee", new BigDecimal("12.99")),
        new Product("P-HOODIE", "APPAREL", "Hoodie", new BigDecimal("39.50")),
        new Product("P-MUG", "HOME", "Coffee Mug", new BigDecimal("6.75")),
        new Product("P-NOTE", "STATIONERY", "Notebook", new BigDecimal("4.10")));
  }

  public static List<Payment> payments() {
    return List.of(
        new CardPayment("4242", "VISA"),
        new CardPayment("4243", "VISA"),
        new CardPayment("1881", "MASTERCARD"),
        new WalletPayment("APPLEPAY"),
        new WalletPayment("PAYPAL"));
  }

  public static List<Order> orders() {
    var cs = customers();
    var ps = products();
    return List.of(
        new Order(
            "O-100",
            cs.get(0),
            List.of(ps.get(0), ps.get(2)),
            Instant.parse("2024-12-01T10:15:30Z")),
        new Order("O-101", cs.get(1), List.of(ps.get(1)), Instant.parse("2025-01-10T11:05:00Z")),
        new Order(
            "O-102",
            cs.get(2),
            List.of(ps.get(0), ps.get(1), ps.get(3)),
            Instant.parse("2025-01-15T09:30:00Z")),
        new Order("O-103", cs.get(0), List.of(ps.get(3)), Instant.parse("2025-02-03T16:20:00Z")));
  }
}

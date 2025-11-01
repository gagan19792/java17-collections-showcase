package dev.gagan.collections.model;

public record Customer(String id, String name, String email, String tier) {
  public Customer {
    if (id == null || id.isBlank())
      try {
        throw new IllegalAccessException("id required");
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
  }
}

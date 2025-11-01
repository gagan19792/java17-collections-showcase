package dev.gagan.collections.model;

import java.time.Instant;
import java.util.List;

public record Order(String id, Customer customer, List<Product> items, Instant placedAt) {}

package dev.gagan.collections.model;

import java.math.BigDecimal;

public record Product(String sku, String category, String title, BigDecimal price) {}

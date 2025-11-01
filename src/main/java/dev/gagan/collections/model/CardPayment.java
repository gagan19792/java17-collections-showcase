package dev.gagan.collections.model;

public record CardPayment(String last4, String network) implements Payment {}

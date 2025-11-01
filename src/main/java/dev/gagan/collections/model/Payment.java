package dev.gagan.collections.model;

public sealed interface Payment permits CardPayment, WalletPayment {}

package dev.gagan.collections.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PaymentTest {

  @Test
  public void patternMatchingInstanceof() {
    var p = new CardPayment("4242", "VISA");
    boolean isVisa = p instanceof CardPayment cp && cp.network().equals("VISA");
    assertTrue(isVisa);
  }
}

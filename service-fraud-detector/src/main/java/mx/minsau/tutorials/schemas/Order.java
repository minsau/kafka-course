package mx.minsau.tutorials.schemas;

import java.math.BigDecimal;

public class Order {
  private String orderId;
  private String userId;
  private BigDecimal amount;

  public Order() {
  }

  public Order(String orderId, String userId, BigDecimal amount) {
    this.orderId = orderId;
    this.userId = userId;
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "Order{" +
        "orderId='" + orderId + '\'' +
        ", userId='" + userId + '\'' +
        ", amount=" + amount +
        '}';
  }
}

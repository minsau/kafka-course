package mx.minsau.tutorials;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import mx.minsau.tutorials.schemas.Order;

public class NewOrderMain {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    
    try (var dispatcher = new KafkaDispatcher<Order>()) {
      for (var i = 0; i < 10; i++) {
        var orderId = UUID.randomUUID().toString();
        var userId = UUID.randomUUID().toString();

        var order = new Order(userId, orderId, new BigDecimal(Math.random() * 5000 + 1));
        dispatcher.send(orderId, order, "ECOMMERCE.NEW-ORDER");
      }
    }
    try (var dispatcher = new KafkaDispatcher<>()) {
      for (var i = 0; i < 10; i++) {
        var orderId = UUID.randomUUID().toString();

        dispatcher.send(orderId, "Your order has been received", "ECOMMERCE.NEW-EMAIL.V2");
      }
    }
  }
  
}

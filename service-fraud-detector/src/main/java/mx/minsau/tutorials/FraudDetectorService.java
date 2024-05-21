package mx.minsau.tutorials;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import mx.minsau.tutorials.schemas.Order;

public class FraudDetectorService {

  public static void main(String[] args) {
    var fraudService = new FraudDetectorService();
    try (
        var consumerService = new KafkaService<>(
            FraudDetectorService.class.getSimpleName(),
            "ECOMMERCE.NEW-ORDER",
            fraudService::consume,
            Order.class,
            Map.of())) {
      consumerService.run();
    }
  }

  private void consume(ConsumerRecord<String, Order> record) {
    StringBuilder sb = new StringBuilder();
    sb.append("Processing new order, checking for fraud ");
    sb.append("Key: " + record.key() + " ");
    sb.append("Value: " + record.value() + " ");
    sb.append("Partition: " + record.partition() + " ");
    sb.append("Offset: " + record.offset() + " ");
    sb.append("Timestamp: " + record.timestamp() + "\n");
    System.out.println(sb.toString());
  }
}

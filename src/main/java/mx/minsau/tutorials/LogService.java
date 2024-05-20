package mx.minsau.tutorials;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

public class LogService {
  public static void main(String[] args) {
    var logService = new LogService();
    try (
        var consumerService = new KafkaService<>(
            EmailService.class.getSimpleName(),
            Pattern.compile("ECOMMERCE.*"),
            logService::consume,
            String.class,
            Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))) {
      consumerService.run();
    }

  }

  private void consume(ConsumerRecord<String, String> record) {
    System.out.println("Processing new order, checking for fraud");
    System.out.println("Key: " + record.key());
    System.out.println("Value: " + record.value());
  }

}

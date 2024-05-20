package mx.minsau.tutorials;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {
  public static void main(String[] args) {
    var emailService = new EmailService();
    try(
    var consumerService = new KafkaService<>(
      EmailService.class.getSimpleName(), 
      "ECOMMERCE.NEW-EMAIL.V2", 
      emailService::consume,
      String.class,
      Map.of()
    )){
      consumerService.run();
    }
    
  }

  private void consume(ConsumerRecord<String, String> record) {
    StringBuilder sb = new StringBuilder();
    sb.append("Processing new email ");
    sb.append("Key: " + record.key() + " ");
    sb.append("Value: " + record.value() + " ");
    sb.append("Partition: " + record.partition() + " ");
    sb.append("Offset: " + record.offset() + " ");
    sb.append("Timestamp: " + record.timestamp() + "\n");
    System.out.println(sb.toString());
  }
}

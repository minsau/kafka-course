package mx.minsau.tutorials;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerFunction<T> {
  
  void consume(ConsumerRecord<String, T> record);

}

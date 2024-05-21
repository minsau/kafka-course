package mx.minsau.tutorials;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaService<T> implements Closeable{

  private final KafkaConsumer<String, T> consumer;
  private final ConsumerFunction<T> fun;
  private Class<T> expectedType;

  public KafkaService(String groupName, String topic, ConsumerFunction<T> fun, Class<T> expectedType, Map<String, String> properties) {
    this.expectedType = expectedType;
    this.fun = fun;
    this.consumer = new KafkaConsumer<>(consumerProperties(groupName, properties));
    this.consumer.subscribe(Collections.singletonList(topic));
  }

  public KafkaService(String groupName, Pattern topicPattern, ConsumerFunction<T> fun, Class<T> expectedType, Map<String, String> properties) {
    this.expectedType = expectedType;
    this.fun = fun;
    this.consumer = new KafkaConsumer<>(consumerProperties(groupName, properties));
    this.consumer.subscribe(topicPattern);
  }

  private Properties consumerProperties(String groupName, Map<String, String> extraProperties) {
    var properties = new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
    properties.put(GsonDeserializer.TYPE_CONFIG, expectedType.getName());
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
    properties.putAll(extraProperties);
    return properties;
  }

  public void run() {
    while (true) {
      var records = this.consumer.poll(Duration.ofMillis(100));
      if (!records.isEmpty()) {
        System.out.println("Found " + records.count() + " records");
        for (var record : records) {
          this.fun.consume(record);
        }
      }
    }
  }

  @Override
  public void close(){
    this.consumer.close();
  }

  
}

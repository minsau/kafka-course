package mx.minsau.tutorials;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaDispatcher<T> implements Closeable{
  private final KafkaProducer<String, T> producer;

  public KafkaDispatcher() {
    this.producer = new KafkaProducer<String, T>(producerProperties());
  }

  public void send(String key, T value, String topic) throws InterruptedException, ExecutionException {
    var record = new ProducerRecord<String, T>(topic, key, value);

    Callback callback = (data, ex) -> {
      if (ex != null) {
        ex.printStackTrace();
        return;
      }
      System.out.println("Success: " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
    };

    this.producer.send(record, callback).get();
  }

  private static Properties producerProperties() {
    var properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
    return properties;
  }

  @Override
  public void close() {
    this.producer.close();
  }
  
}

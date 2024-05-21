package mx.minsau.tutorials;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonDeserializer<T> implements Deserializer<T>{

  public static final String TYPE_CONFIG = "String";
  private Class<T> targetType;

  private final Gson gson = new GsonBuilder().create();

  @SuppressWarnings("unchecked")
  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    String typeName = (String) configs.get(TYPE_CONFIG);
    try {
      this.targetType = (Class<T>) Class.forName(typeName);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Type cannot be found");
    }
  }

  @Override
  public T deserialize(String s, byte[] data) {
    return gson.fromJson(new String(data), this.targetType);
  }
  
}

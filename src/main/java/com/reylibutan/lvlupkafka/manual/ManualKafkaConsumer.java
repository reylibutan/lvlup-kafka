package com.reylibutan.lvlupkafka.manual;

import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class ManualKafkaConsumer {

  public static void main(String[] args) {
    // initialize the props, similar to how you would be calling kafka-console-consumer.bat in CLI
    Properties props = new Properties();
    props.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


  }
}

package com.reylibutan.lvlupkafka.basics;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * This is a sample implementation where we only use the basic low-level Kafka Producer API. <br>
 * No usage of any Spring-related stuff.
 */
@Slf4j
public class ManualKafkaProducer {

  public static void main(String[] args) {
    // initialize the props, similar to how you would be calling kafka-console-producer.bat in CLI
    Properties props = new Properties();
    props.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    try (KafkaProducer<String, String> localKafkaProducer = new KafkaProducer<>(props)) {
      // even though these messages don't have a key, they will be sent to the same partition because of Sticky Partitioning
      // contrary to what we expect which is round-robin
      for (int i = 0; i < 10; i++) {
        ProducerRecord<String, String> sampleRecord = new ProducerRecord<>("demo_java", "key_" + i, "this is a sample message " + i);
        localKafkaProducer.send(sampleRecord, (metadata, exception) -> {
          if (exception == null) {
            log.info("Successfully sent a message. \n{ topic: {}, partition: {}, offset: {}, timestamp: {} }", metadata.topic(), metadata.partition(), metadata.offset(),
                metadata.timestamp());
          } else {
            log.error("", exception);
          }
        });

        // to force these messages to be sent to different partitions (e.g. bypass Sticky Partitioning), we can do some thread sleep here
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          log.error("error", e);
//        }
      }
    }
  }
}

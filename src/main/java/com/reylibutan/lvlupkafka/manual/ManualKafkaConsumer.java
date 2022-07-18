package com.reylibutan.lvlupkafka.manual;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Slf4j
public class ManualKafkaConsumer {

  public static void main(String[] args) {
    String bootstrapServers = "localhost:9092";
    String consumerGroupId = "manual-consumer-group";
    String topic = "demo_java";

    Properties props = new Properties();
    props.setProperty(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    props.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    props.setProperty(GROUP_ID_CONFIG, consumerGroupId);
    props.setProperty(AUTO_OFFSET_RESET_CONFIG, "earliest");

    try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
      consumer.subscribe(List.of(topic));

      final Thread mainThread = Thread.currentThread();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        log.info("Detected a shutdown - let's exit by calling consumer.wakeup()...");
        consumer.wakeup();

        try {
          mainThread.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }));

      while (true) {
        log.info("<<< Polling....");

        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
          log.info("key: {}, value: {}", record.key(), record.value());
          log.info("partition: {}, offset: {}", record.partition(), record.value());
        }
      }

    } catch (WakeupException e) {
      log.info("Wake up exception! We do nothing...");
    } catch (Exception e) {
      log.error("Unexpected exception");
    }
  }
}

package com.reylibutan.lvlupkafka;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafkaStreams
@SpringBootApplication
public class LvlupKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(LvlupKafkaApplication.class, args);
  }
}

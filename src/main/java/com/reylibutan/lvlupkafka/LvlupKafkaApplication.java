package com.reylibutan.lvlupkafka;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LvlupKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(LvlupKafkaApplication.class, args);
  }

  @Bean
  public Faker faker() {
    return new Faker();
  }
}

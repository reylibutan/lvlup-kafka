package com.reylibutan.lvlupkafka.spring.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

//@Configuration
//TODO unused
public class KafkaStreamsConfig {

  @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
  public KafkaStreamsConfiguration kStreamsConfig() {
    return new KafkaStreamsConfiguration(Map.of(
        APPLICATION_ID_CONFIG, "testStreams",
        BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
        DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName(),
        DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
        DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName()
    ));
  }
}

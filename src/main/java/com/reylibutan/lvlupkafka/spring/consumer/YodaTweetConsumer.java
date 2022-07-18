package com.reylibutan.lvlupkafka.spring.consumer;

import com.reylibutan.lvlupkafka.spring.tweet.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class YodaTweetConsumer {

  @KafkaListener(topics = {"lvlupkafka.core.yodatweets"})
  public void consume(ConsumerRecord<String, Tweet> tweetRecord) {
    log.info("    <<<  {}", tweetRecord.value().getText());
  }
}

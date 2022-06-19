package com.reylibutan.lvlupkafka.streams;

import com.reylibutan.lvlupkafka.tweet.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class YodaTweetStreamProcessor {

  @Value("${lvlupkafka.topics.core.yodatweets}")
  private String TOPIC_YODA_TWEETS;

  @Autowired
  public void filterQuestions(StreamsBuilder builder) {
    KStream<String, Tweet> yodaTweetStream =
        builder.stream(
            TOPIC_YODA_TWEETS, Consumed.with(Serdes.String(), new JsonSerde<>(Tweet.class)));

    yodaTweetStream.peek(
        (key, tweet) -> {
          try {
            log.info("[KS] <<< K={}, V={}", key, tweet);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }
}

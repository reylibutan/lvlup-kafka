package com.reylibutan.lvlupkafka.spring.producer;

import com.reylibutan.lvlupkafka.spring.tweet.Tweet;
import com.reylibutan.lvlupkafka.spring.tweet.TweetGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//@Component
@Slf4j
@RequiredArgsConstructor
public class YodaTweetProducer {

  private final TweetGenerator tweetGenerator;
  private final KafkaTemplate<String, Tweet> kTemplate;
  @Value("${lvlupkafka.topics.core.yodatweets}")
  private String TOPIC_YODA_TWEETS;

  @EventListener(ApplicationStartedEvent.class)
  public void generateFakeYodaQuotes() {
    ScheduledExecutorService scheduledJob = Executors.newSingleThreadScheduledExecutor();
    scheduledJob.scheduleAtFixedRate(this::generateYodaTweet, 1, 3_000, TimeUnit.MILLISECONDS);
  }

  public void generateYodaTweet() {
    String key = UUID.randomUUID().toString();

    try {
      Tweet tweet = tweetGenerator.generateYodaTweet();
      log.info(">>> Sending Yoda Tweet. (key={}, tweet={})", key, tweet);
      kTemplate.send(TOPIC_YODA_TWEETS, key, tweet);
    } catch (Exception e) {
      log.error("Unable to generate a cool Yoda tweet -_-. {key: {}}", key, e);
    }
  }
}

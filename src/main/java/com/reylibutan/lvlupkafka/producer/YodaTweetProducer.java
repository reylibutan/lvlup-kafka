package com.reylibutan.lvlupkafka.producer;

import com.reylibutan.lvlupkafka.tweet.Tweet;
import com.reylibutan.lvlupkafka.tweet.TweetGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class YodaTweetProducer {

  private final TweetGenerator tweetGenerator;
  private final KafkaTemplate<String, Tweet> kTemplate;

  @EventListener(ApplicationStartedEvent.class)
  public void generateFakeYodaQuote() {
    ScheduledExecutorService scheduledJob = Executors.newSingleThreadScheduledExecutor();
    scheduledJob.scheduleAtFixedRate(this::generateYodaTweet, 1, 3_000, TimeUnit.MILLISECONDS);
  }

  public void generateYodaTweet() {
    try {
      String key = UUID.randomUUID().toString();
      Tweet tweet = tweetGenerator.generateYodaTweet();

      log.info(">>> Sending Yoda Tweet. (key={}, tweet={})", key, tweet);
      kTemplate.send("yoda-tweets", key, tweet);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
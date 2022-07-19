package com.reylibutan.lvlupkafka.spring.tweet;

import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TweetGenerator {

  private final Faker faker = new Faker();

  public Tweet generateYodaTweet() {
    Long yodaId = 1029384756L;
    String yodaUsername = "Yoda Man | Chilling";

    Tweet yodaTweet = new Tweet();
    yodaTweet.setId(Long.valueOf(faker.twitter().twitterId(10)));
    yodaTweet.setText(faker.yoda().quote());
    yodaTweet.setCreatedAt(LocalDateTime.now());
    yodaTweet.setAuthorId(yodaId);
    yodaTweet.setUsername(yodaUsername);

    return yodaTweet;
  }
}

package com.reylibutan.lvlupkafka.tweet;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Tweet {

  private Long id;
  private String text;
  private LocalDateTime createdAt;
  private Long authorId;
  private String username;
}

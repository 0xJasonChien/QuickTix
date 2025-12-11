package com.jason.quicktix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // <--- 加上這行，BaseEntity 的時間才會自動生成
public class QuicktixApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuicktixApplication.class, args);
  }
}

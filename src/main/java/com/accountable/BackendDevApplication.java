package com.accountable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.accountable.repository")
@EntityScan(basePackages = "com.accountable.entity")
public class BackendDevApplication {
  public static void main(String[] args) {
    SpringApplication.run(BackendDevApplication.class, args);
  }
}

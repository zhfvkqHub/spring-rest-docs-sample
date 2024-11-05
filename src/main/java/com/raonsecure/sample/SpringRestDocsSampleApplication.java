package com.raonsecure.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = "com.raonsecure.sample.entity")
public class SpringRestDocsSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestDocsSampleApplication.class, args);
    }
}

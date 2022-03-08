package com.payment.oauth;

import com.payment.oauth.config.CustomRequestEntityConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OauthTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthTestTaskApplication.class, args);
    }

}

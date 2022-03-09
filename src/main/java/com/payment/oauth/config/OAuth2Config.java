package com.payment.oauth.config;

import com.payment.oauth.service.SandboxService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class OAuth2Config {

    @Value("${spring.security.oauth2.client.registration.SANDBOX.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.SANDBOX.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.SANDBOX.base-uri}")
    private String baseUri;
    @Value("${spring.security.oauth2.client.registration.SANDBOX.redirect-uri}")
    private String redirectUri;
    @Value("${spring.security.oauth2.client.registration.SANDBOX.scope}")
    private String[] scope;

    @Value("${spring.security.oauth2.client.provider.SANDBOX.authorization-uri}")
    private String authUri;
    @Value("${spring.security.oauth2.client.provider.SANDBOX.token-uri}")
    private String tokenUri;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.sandboxClientRegistration());
    }

    private ClientRegistration sandboxClientRegistration() {
        return ClientRegistration.withRegistrationId("SANDBOX")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope(scope)
                .authorizationUri(authUri)
                .tokenUri(tokenUri)
                .build();
    }

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUri)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public SandboxService sandboxService() {
        return retrofit().create(SandboxService.class);
    }
}

package com.payment.oauth.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;

public class CustomRequestEntityConverter implements Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> {

    private final OAuth2AuthorizationCodeGrantRequestEntityConverter defaultConverter;

    public CustomRequestEntityConverter() {
        defaultConverter = new OAuth2AuthorizationCodeGrantRequestEntityConverter();
    }

    @Override
    public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest req) {
        ClientRegistration clientRegistration = req.getClientRegistration();
        RequestEntity<?> entity = defaultConverter.convert(req);

        if (entity == null) {
            return null;
        }

        MultiValueMap<String, String> body = (MultiValueMap<String, String>) entity.getBody();
        if (body == null) {
            body = new MultiValueMapAdapter<>(new HashMap<>());
        }
        body.add("client_id", clientRegistration.getClientId());
        return new RequestEntity<>(body, entity.getHeaders(), entity.getMethod(), entity.getUrl());
    }

}
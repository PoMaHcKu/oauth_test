package com.payment.oauth.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
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

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.putAll(entity.getHeaders());
        headers.add("Accept", "*/*");

        MultiValueMap<String, String> body = (MultiValueMap<String, String>) entity.getBody();
        if (body == null) {
            body = new MultiValueMapAdapter<>(new HashMap<>());
        }
        body.add("client_id", clientRegistration.getClientId());
        body.add("client_secret", clientRegistration.getClientSecret());
        body.put("scope", new ArrayList<>(clientRegistration.getScopes()));
        return new RequestEntity<>(body, headers, entity.getMethod(), entity.getUrl());
    }

}
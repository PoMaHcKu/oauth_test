package com.payment.oauth.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomTokenResponseConverter implements Converter<Map<String, Object>, OAuth2AccessTokenResponse> {

    private static final String ACCESS_TOKEN = "accessToken";
    private static final String TOKEN_TYPE = "tokenType";
    private static final String EXPIRES_IN = "expiresIn";
    private static final String SCOPE = "scope";
    private static final String REFRESH_TOKEN = "refreshToken";

    @Override
    public OAuth2AccessTokenResponse convert(Map<String, Object> source) {

        Map<String, String> strSource = source
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            final Object value = e.getValue();
                            if (value instanceof String strVal) return strVal;
                            else if (value == null) return "";
                            else throw new RuntimeException("Unsupported type");
                        }));

        String accessToken = strSource.get(ACCESS_TOKEN);

        Set<String> scopes = Collections.emptySet();
        if (strSource.containsKey(SCOPE)) {
            String scope = strSource.get(SCOPE);
            scopes = StringUtils.commaDelimitedListToSet(scope);
        }

        OAuth2AccessToken.TokenType tokenType = OAuth2AccessToken.TokenType.BEARER;
        String tokenTypeValue = strSource.get(TOKEN_TYPE);
        if (!tokenType.getValue().equals(tokenTypeValue)) {
            throw new RuntimeException("Unknown token type: " + tokenTypeValue  );
        }

        long expiresIn = Long.parseLong(strSource.get(EXPIRES_IN));
        String refreshToken = strSource.get(REFRESH_TOKEN);

        return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(tokenType)
                .expiresIn(expiresIn)
                .scopes(scopes)
                .refreshToken(refreshToken)
                .additionalParameters(Map.of(
                        "username", "user",
                        "token", accessToken))
                .build();
    }
}

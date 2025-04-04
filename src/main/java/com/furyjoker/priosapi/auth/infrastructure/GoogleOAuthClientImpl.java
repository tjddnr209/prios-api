package com.furyjoker.priosapi.auth.infrastructure;

import com.furyjoker.priosapi.auth.domain.AuthMember;
import com.furyjoker.priosapi.global.exception.PriosException;
import com.furyjoker.priosapi.global.exception.type.AuthErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class GoogleOAuthClientImpl implements GoogleOAuthClient {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String userInfoUri;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public AuthMember getUserInfo(String code) {
        String accessToken = getAccessToken(code);
        return fetchUser(accessToken);
    }

    private String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "code=" + code
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectUri
                + "&grant_type=authorization_code";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(tokenUri, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new PriosException(AuthErrorCode.FAIL_TO_GET_TOKEN);
        }

        return (String) response.getBody().get("access_token");
    }

    private AuthMember fetchUser(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new PriosException(AuthErrorCode.FAIL_TO_GET_USER_INFO);
        }

        Map<String, Object> userInfo = response.getBody();

        return new AuthMember(
                (String) userInfo.get("sub"),
                (String) userInfo.get("name"),
                (String) userInfo.get("email"),
                (String) userInfo.get("picture")
        );
    }
}

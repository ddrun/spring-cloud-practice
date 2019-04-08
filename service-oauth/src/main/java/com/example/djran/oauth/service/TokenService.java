package com.example.djran.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenStore tokenStore;

    /**
     * oauth2 认证服务器直接处理校验请求的逻辑
     *
     * @param accessToken
     * @return
     */
    public OAuth2AccessToken checkTokenInOauth2Server(String accessToken) {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        return oAuth2AccessToken;
    }

    /**
     * oauth2 认证服务器直接处理校验请求的逻辑
     *
     * @param accessToken
     * @return
     */
    public OAuth2Authentication getAuthenticationInOauth2Server(String accessToken) {
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
        return oAuth2Authentication;
    }
}

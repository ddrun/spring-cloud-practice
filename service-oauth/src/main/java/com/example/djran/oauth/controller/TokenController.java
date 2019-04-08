package com.example.djran.oauth.controller;

import com.alibaba.fastjson.JSON;
import com.example.djran.oauth.service.TokenService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author dengjr
 * @function
 * @time 2018/8/11
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class TokenController {
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private TokenService tokenService;

    /**
     * 重载Oauth2获取token接口
     * @param principal
     * @param parameters
     * @return
     */
    @PostMapping("/token")
    public OAuth2Token postAccessToken(Principal principal, @RequestParam Map<String,String> parameters, String client_id){
        parameters.put("grant_type", "client_credentials");
        try{
            parameters.put("client_id",client_id);
            long time = System.currentTimeMillis();
            OAuth2AccessToken accessToken=tokenEndpoint.postAccessToken(principal,parameters).getBody();
            log.info("\n 用户获取token消耗时间为：" + (System.currentTimeMillis() - time) +"\n"+
                    " 请求参数为：" + JSON.toJSONString(parameters)+"\n"+
                    "  获取token：" + JSON.toJSONString(accessToken)+"\n");
            return new OAuth2Token(accessToken);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/check-token")
    public OAuth2Token getToken(@RequestParam(value = "access_token") String token) {
        OAuth2AccessToken oAuth2AccessToken = tokenService.checkTokenInOauth2Server(token);
        return new OAuth2Token(oAuth2AccessToken);
    }
    /**
     * 获取当前token对应的用户主体的凭证信息(认证对象)
     */
    @GetMapping("/get-auth")
    public Object getAuth(Principal principal, @RequestParam(value = "access_token", required = false) String token) {
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            return oAuth2Authentication.getOAuth2Request();
        }
        OAuth2Authentication oAuth2Authentication = tokenService.getAuthenticationInOauth2Server(token);
        return oAuth2Authentication;
    }
    /**
     * 自定义token数据结构
     */
    @Data
    @AllArgsConstructor
    public static class OAuth2Token{
        public OAuth2Token(OAuth2AccessToken token){
            this.access_token=token.getValue();
            this.expiration=token.getExpiration();
            this.token_type=token.getTokenType();
            this.scope=token.getScope();
            if (token.getRefreshToken() != null) {
                this.refresh_token = token.getRefreshToken().getValue();
            }
        }
        private String access_token;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date expiration;
        private String token_type;
        private String refresh_token;
        private Set<String> scope;

    }
}

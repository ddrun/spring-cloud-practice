package com.example.djran.oauth.service;

import com.example.djran.oauth.dao.DeveloperDao;
import com.example.djran.oauth.model.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author dengjr
 * @function
 * @time 2018/8/11
 */
@Service
public class OauthClientService implements ClientDetailsService {

    //默认 access_token有效期 6小时
    public static final int VALIDITY_SECONDS = 6 * 60 * 60;
    //默认 refresh_token有效期 30天
    public static final int REFRESH_VALIDITY_SECONDS = 30 * 24 * 60 * 60;
    @Autowired
    private DeveloperDao developerDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Developer developer = developerDao.findDeveloperByAppId(clientId);
        if(developer==null){
            return null;
        }
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(clientId);
        baseClientDetails.setClientSecret(bCryptPasswordEncoder.encode(developer.getAppSecret()));
        baseClientDetails.setAccessTokenValiditySeconds(VALIDITY_SECONDS);
        baseClientDetails.setRefreshTokenValiditySeconds(REFRESH_VALIDITY_SECONDS);
        baseClientDetails.setScope(Arrays.asList("read", "write"));
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList("password", "client_credentials", "refresh_token", "authorization_code"));
        baseClientDetails.setAutoApproveScopes(Arrays.asList("true"));
        return baseClientDetails;
    }
}

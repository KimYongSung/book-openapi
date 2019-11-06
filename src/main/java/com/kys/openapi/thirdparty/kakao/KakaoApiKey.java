package com.kys.openapi.thirdparty.kakao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@ConfigurationProperties(prefix = "thirdparty.kakao")
@NoArgsConstructor
public class KakaoApiKey implements InitializingBean {

    @Setter
    private String restApiKey;

    @Setter
    private String keyPrefix;

    private String key;

    public KakaoApiKey(String restApiKey, String keyPrefix) {
        this.restApiKey = restApiKey;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public void afterPropertiesSet() {
        this.key = String.format("%s %s", keyPrefix, restApiKey);
    }

    public HttpHeaders makeHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, key);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        return headers;
    }

    public void addKakaoAuthorization(HttpHeaders headers){
        if(Objects.isNull(headers.get(HttpHeaders.AUTHORIZATION))){
            headers.add(HttpHeaders.AUTHORIZATION, key);
        }
    }
}

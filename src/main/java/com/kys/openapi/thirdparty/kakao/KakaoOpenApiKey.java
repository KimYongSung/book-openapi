package com.kys.openapi.thirdparty.kakao;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 카카오 OpenAPI Key 관리
 */
@Component
@ConfigurationProperties(prefix = "thirdparty.kakao")
@NoArgsConstructor
public class KakaoOpenApiKey implements InitializingBean {

    @Setter
    private String restApiKey;

    @Setter
    private String keyPrefix;

    private String key;

    public KakaoOpenApiKey(String restApiKey, String keyPrefix) {
        this.restApiKey = restApiKey;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public void afterPropertiesSet() {
        this.key = String.format("%s %s", keyPrefix, restApiKey);
    }

    /**
     * 카카오 OpenAPI 요청용 Http 헤더 생성
     * @return
     */
    public HttpHeaders makeHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, key);
        return headers;
    }

    /**
     * Http 헤더에 카카오 인증 정보 추가
     * @param headers
     */
    public void addKakaoAuthorization(HttpHeaders headers){
        if(Objects.isNull(headers.get(HttpHeaders.AUTHORIZATION))){
            headers.add(HttpHeaders.AUTHORIZATION, key);
        }
    }
}

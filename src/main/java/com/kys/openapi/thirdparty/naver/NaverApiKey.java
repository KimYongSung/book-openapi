package com.kys.openapi.thirdparty.naver;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@ConfigurationProperties(prefix = "thirdparty.naver")
public class NaverApiKey {

    /**
     *  네이버 openAPI 요청 ID 용 http 헤더
     */
    protected static final String NAVER_CLIENT_ID = "X-Naver-Client-Id";

    /**
     *  네이버 openAPI 요청 SECRET 용 http 헤더
     */
    protected static final String NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    @Setter
    private String clientId;

    @Setter
    private String secret;

    public void addNaverApiKey(HttpHeaders httpHeaders){

        if(Objects.isNull(httpHeaders.get(NAVER_CLIENT_ID))){
            httpHeaders.add(NAVER_CLIENT_ID, clientId);
        }else if(Objects.isNull(httpHeaders.get(NAVER_CLIENT_SECRET))){
            httpHeaders.add(NAVER_CLIENT_SECRET, secret);
        }
    }

    public HttpHeaders makeHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(NAVER_CLIENT_ID, clientId);
        httpHeaders.add(NAVER_CLIENT_SECRET, secret);
        return httpHeaders;
    }
}

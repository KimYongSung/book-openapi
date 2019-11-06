package com.kys.openapi.thirdparty.kakao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kys.openapi.thirdparty.network.AbstractSendRecv;
import com.kys.openapi.thirdparty.network.NetWorkConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;

/**
 * 카카오 Open API 송수신 처리자
 */
@Slf4j
@Component
public class KakaoSendRecv extends AbstractSendRecv {

    private KakaoApiKey kakaoApiKey;

    public KakaoSendRecv(RestTemplateBuilder builder, KakaoApiKey kakaoApiKey) {
        super(builder);
        this.kakaoApiKey = kakaoApiKey;
    }

    /**
     * 로깅 interceptor 생성
     * @return
     */
    @Override
    public ClientHttpRequestInterceptor loggingInterceptor(){
        return (request, body, execution) -> {

            URI uri = request.getURI();

            log.info("Request URI : {} ", uri);

            ClientHttpResponse response = execution.execute(request, body);

            log.info("response status : [{}]", response.getStatusCode());
            return response;
        };
    }

    /**
     * Header에 정보 추가
     * @param headers
     */
    public void addAuthorization(HttpHeaders headers){
        kakaoApiKey.addKakaoAuthorization(headers);
    }

    /**
     * Header 생성
     * @return
     */
    public HttpHeaders makeHeaders(){
        return kakaoApiKey.makeHeaders();
    }

}

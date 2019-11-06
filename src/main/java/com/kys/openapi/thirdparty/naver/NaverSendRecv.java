package com.kys.openapi.thirdparty.naver;

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
 * 네이버 Open API 송수신 처리자
 */
@Slf4j
@Component
public class NaverSendRecv extends AbstractSendRecv {

    private NaverApiKey naverApiKey;

    public NaverSendRecv(RestTemplateBuilder builder, NaverApiKey naverApiKey) {
        super(builder);
        this.naverApiKey = naverApiKey;
    }


    /**
     * 로깅 interceptor 생성
     * @return
     */
    @Override
    public ClientHttpRequestInterceptor loggingInterceptor(){
        return (request, body, execution) -> {

            URI uri = request.getURI();
            Map<String, String> header = request.getHeaders().toSingleValueMap();

            log.info("Request URI : {} ", uri);
            log.info("Content-type : {} ", header.get(HttpHeaders.CONTENT_TYPE));
            log.info("{} : {}", NaverApiKey.NAVER_CLIENT_ID, header.get(NaverApiKey.NAVER_CLIENT_ID));
            log.info("{} : {}", NaverApiKey.NAVER_CLIENT_SECRET, header.get(NaverApiKey.NAVER_CLIENT_SECRET));

            ClientHttpResponse response = execution.execute(request, body);

            log.info("response status : [{}]", response.getStatusCode());

            return response;
        };
    }

    @Override
    public void addAuthorization(HttpHeaders headers) {
        naverApiKey.addNaverApiKey(headers);
    }

    @Override
    public HttpHeaders makeHeaders() {
        return naverApiKey.makeHeader();
    }

}

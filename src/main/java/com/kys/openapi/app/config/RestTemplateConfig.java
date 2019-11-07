package com.kys.openapi.app.config;

import com.kys.openapi.thirdparty.network.NetWorkConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Slf4j
@Configuration
@AllArgsConstructor
public class RestTemplateConfig {

    private NetWorkConfig netWorkConfig;

    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder().setConnectTimeout(netWorkConfig.getConnectionTimeOut())
                                        .setReadTimeout(netWorkConfig.getRecvTimeOut())
                                        .defaultMessageConverters()
                                        .additionalInterceptors(loggingInterceptor());
    }

    public ClientHttpRequestInterceptor loggingInterceptor(){
        return (request, body, execution) -> {

            URI uri = request.getURI();

            log.info("Request URI : {} ", uri);

            ClientHttpResponse response = execution.execute(request, body);

            log.info("response status : [{}]", response.getStatusCode());

            return response;
        };
    }
}

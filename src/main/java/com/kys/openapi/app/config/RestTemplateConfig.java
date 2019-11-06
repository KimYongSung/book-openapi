package com.kys.openapi.app.config;

import com.kys.openapi.thirdparty.network.NetWorkConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfig {

    @Autowired
    private NetWorkConfig netWorkConfig;

    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder().setConnectTimeout(netWorkConfig.getConnectionTimeOut())
                                        .setReadTimeout(netWorkConfig.getRecvTimeOut());
    }
}

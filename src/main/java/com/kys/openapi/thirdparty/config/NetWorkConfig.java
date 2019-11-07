package com.kys.openapi.thirdparty.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "thirdparty.network")
@NoArgsConstructor
@AllArgsConstructor
public class NetWorkConfig {

    @Setter
    private int connectionTimeOut;

    @Setter
    private int recvTimeOut;

    public Duration getConnectionTimeOut(){
        return Duration.ofMillis(connectionTimeOut);
    }

    public Duration getRecvTimeOut(){
        return Duration.ofMillis(recvTimeOut);
    }
}

package com.kys.openapi.thirdparty;

import com.kys.openapi.thirdparty.config.NetWorkConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class NetWorkConfigTest {

    private static NetWorkConfig netWorkConfig;

    @BeforeClass
    public static void setUp(){
        netWorkConfig = new NetWorkConfig();
        netWorkConfig.setConnectionTimeOut(3000);
        netWorkConfig.setRecvTimeOut(3000);
    }

    @Test
    public void 네트워크_환경설정_정보확인(){
        Duration connectionTimeOut = netWorkConfig.getConnectionTimeOut();
        Duration recvTimeOut= netWorkConfig.getRecvTimeOut();

        assertThat(connectionTimeOut.getSeconds()).isEqualTo(3);
        assertThat(recvTimeOut.getSeconds()).isEqualTo(3);
    }
}

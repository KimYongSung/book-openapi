package com.kys.openapi.thirdparty.kakao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoApiKeyTest {

    @Autowired
    private KakaoApiKey apiKey;

    @Test
    public void 카카오_APIKEY_PROPERTIES_정보확인(){
        assertThat(apiKey).hasFieldOrPropertyWithValue("restApiKey", "355455c96ccc2c1b228bb552f56d5b44")
                          .hasFieldOrPropertyWithValue("keyPrefix", "KakaoAK")
                          .hasFieldOrPropertyWithValue("key", "KakaoAK 355455c96ccc2c1b228bb552f56d5b44");
    }

    @Test
    public void 카카오_HTTP_헤더생성_확인(){

        HttpHeaders header = apiKey.makeHeaders();

        assertThat(header).containsEntry(HttpHeaders.AUTHORIZATION, Arrays.asList("KakaoAK 355455c96ccc2c1b228bb552f56d5b44"));
    }

    @Test
    public void 카카오_HTTP_헤더추가_확인(){

        HttpHeaders header = new HttpHeaders();

        apiKey.addKakaoAuthorization(header);

        assertThat(header).containsEntry(HttpHeaders.AUTHORIZATION, Arrays.asList("KakaoAK 355455c96ccc2c1b228bb552f56d5b44"));
    }
}

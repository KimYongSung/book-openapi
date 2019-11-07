package com.kys.openapi.thirdparty.naver;

import com.kys.openapi.app.config.RestTemplateConfig;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookDetailSearchRequest;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookItem;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookSearchRequest;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookSearchResponse;
import com.kys.openapi.thirdparty.network.NetWorkConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NaverOpenApiTemplateTest {

    private static NaverOpenApiTemplate naverOpenApiTemplate;

    @BeforeClass
    public static void setUp(){

        RestTemplateConfig restTemplateConfig = new RestTemplateConfig(new NetWorkConfig(3000, 3000));
        NaverOpenApiKey apiKey = new NaverOpenApiKey("jCFIRiVPohq6KLt1VNcY", "IyL2fs4lVA");
        naverOpenApiTemplate = new NaverOpenApiTemplate(restTemplateConfig.restTemplateBuilder(), apiKey);
    }

    @Test(expected = RestClientException.class)
    public void 잘못된_key_요청(){

        NaverOpenApiKey apiKey = new NaverOpenApiKey("jCFIRiVPohq6KLt1VNcY", "2IyL2fs4lVA");
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig(new NetWorkConfig(3000, 3000));
        NaverOpenApiTemplate naverOpenApiTemplate = new NaverOpenApiTemplate(restTemplateConfig.restTemplateBuilder(), apiKey);
        // given
        NaverBookSearchRequest request = new NaverBookSearchRequest("상실의 시대");

        // when
        ResponseEntity<NaverBookSearchResponse> response = naverOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test(expected = RestClientException.class)
    public void 부적절한_DISPLAY값(){

        NaverOpenApiKey apiKey = new NaverOpenApiKey("jCFIRiVPohq6KLt1VNcY", "2IyL2fs4lVA");
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig(new NetWorkConfig(3000, 3000));
        NaverOpenApiTemplate naverOpenApiTemplate = new NaverOpenApiTemplate(restTemplateConfig.restTemplateBuilder(), apiKey);
        // given
        NaverBookSearchRequest request = NaverBookSearchRequest.builder()
                                                               .display(101)
                                                               .query("상실의 시대")
                                                               .build();

        // when
        ResponseEntity<NaverBookSearchResponse> response = naverOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 네이버_책_검색(){
        // given
        NaverBookSearchRequest request = new NaverBookSearchRequest("상실의 시대");

        // when
        ResponseEntity<NaverBookSearchResponse> response = naverOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        NaverBookSearchResponse body = response.getBody();
        NaverBookSearchResponse.Channel channel = body.getChannel();
        List<NaverBookItem> items = channel.getItems();

        assertThat(items.size()).isEqualTo(channel.getDisplay());
    }

    @Test
    public void 네이버_책_상세검색(){
        // given
        NaverBookDetailSearchRequest request = NaverBookDetailSearchRequest.builder()
                                                                           .dIsbn("4770022328 9784770022325")
                                                                           .build() ;

        // when
        ResponseEntity<NaverBookSearchResponse> response = naverOpenApiTemplate.bookDetailSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        NaverBookSearchResponse body = response.getBody();
        NaverBookSearchResponse.Channel channel = body.getChannel();
        List<NaverBookItem> items = channel.getItems();

        assertThat(channel.getDisplay()).isEqualTo(1);
        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0)).hasFieldOrPropertyWithValue("isbn", "4770022328 9784770022325");
    }
}

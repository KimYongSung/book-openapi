package com.kys.openapi.thirdparty.kakao;

import com.kys.openapi.thirdparty.config.NetWorkConfig;
import com.kys.openapi.thirdparty.config.RestTemplateConfig;
import com.kys.openapi.thirdparty.kakao.code.BookTarget;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchDocument;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchRequest;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchResponse;
import com.kys.openapi.thirdparty.kakao.search.common.SearchMeta;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KakaoOpenApiUrlRestTemplateTest {

    public static KakaoOpenApiRestTemplate kakaoOpenApiTemplate;

    @BeforeClass
    public static void setUp(){
        KakaoOpenApiKey kakaoApiKey = new KakaoOpenApiKey("355455c96ccc2c1b228bb552f56d5b44","KakaoAK");
        kakaoApiKey.afterPropertiesSet();
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig(new NetWorkConfig(3000, 3000));
        kakaoOpenApiTemplate = new KakaoOpenApiRestTemplate(restTemplateConfig.restTemplateBuilder(), kakaoApiKey);
    }

    @Test
    public void 책정보_조회_테스트(){

        // given
        KakaoBookSearchRequest request = new KakaoBookSearchRequest("상실의시대");

        // when
        ResponseEntity<KakaoBookSearchResponse> response = kakaoOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        KakaoBookSearchResponse body            = response.getBody();
        SearchMeta meta                         = body.getMeta();
        List<KakaoBookSearchDocument> documents = body.getDocuments();
        assertThat(body.isNext()).isEqualTo(true);
    }

    @Test
    public void 책정보_마지막_페이지조회_테스트(){

        // given
        KakaoBookSearchRequest request = KakaoBookSearchRequest.builder()
                                                               .query("상실의시대")
                                                               .page(1)
                                                               .size(5)
                                                               .build();

        // when
        ResponseEntity<KakaoBookSearchResponse> response = kakaoOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        KakaoBookSearchResponse body            = response.getBody();
        SearchMeta meta                         = body.getMeta();
        List<KakaoBookSearchDocument> documents = body.getDocuments();

        assertThat(body.isNext()).isEqualTo(true);
    }

    @Test
    public void 책정보_상세조회_테스트(){

        // given
        KakaoBookSearchRequest request = KakaoBookSearchRequest.builder()
                                                               .query("9791157060597")
                                                               .target(BookTarget.isbn)
                                                               .build();

        // when
        ResponseEntity<KakaoBookSearchResponse> response = kakaoOpenApiTemplate.bookSearch(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        KakaoBookSearchResponse body            = response.getBody();
        SearchMeta meta                         = body.getMeta();
        List<KakaoBookSearchDocument> documents = body.getDocuments();

        assertThat(meta.getPageableCount()).isEqualTo(documents.size());
    }

    @Test(expected = RestClientException.class)
    public void 잘못된_인증키_요청(){

        // given
        KakaoOpenApiRestTemplate kakaoOpenApiTemplate = getInvalidKakaoKeyReq();

        KakaoBookSearchRequest request = new KakaoBookSearchRequest("상실의시대");

        // when
        ResponseEntity<KakaoBookSearchResponse> response = kakaoOpenApiTemplate.bookSearch(request);

    }

    private KakaoOpenApiRestTemplate getInvalidKakaoKeyReq() {
        KakaoOpenApiKey kakaoApiKey = new KakaoOpenApiKey("TEST55c96ccc2c1b228bb552f56d5b44","KakaoAK");
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig(new NetWorkConfig(3000, 3000));
        return new KakaoOpenApiRestTemplate(restTemplateConfig.restTemplateBuilder(), kakaoApiKey);
    }
}

package com.kys.openapi.thirdparty.kakao;

import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookDocument;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookRequest;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookResponse;
import com.kys.openapi.thirdparty.kakao.search.common.SearchMeta;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KakaoSendRecvTest {

    public static KakaoSendRecv kakaoSendRecv;

    @BeforeClass
    public static void setUp(){

        KakaoApiKey kakaoApiKey = new KakaoApiKey("355455c96ccc2c1b228bb552f56d5b44","KakaoAK");
        kakaoApiKey.afterPropertiesSet();

        kakaoSendRecv = new KakaoSendRecv(new RestTemplateBuilder(), kakaoApiKey);
    }

    @Test
    public void 책정보_조회_테스트(){

        // given
        KakaoBookRequest request = new KakaoBookRequest("상실의시대");

        // when
        ResponseEntity<KakaoBookResponse> response = kakaoSendRecv.get(KakaoApi.BOOK_SEARCH, request, KakaoBookResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        KakaoBookResponse body            = response.getBody();
        SearchMeta meta                   = body.getMeta();
        List<KakaoBookDocument> documents = body.getDocuments();

        assertThat(meta.getPageableCount()).isEqualTo(documents.size());

    }
}

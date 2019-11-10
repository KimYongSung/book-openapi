package com.kys.openapi.book.service;

import com.kys.openapi.app.exception.ThirdPartyApiResultNotFoundException;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookDetailDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.thirdparty.kakao.KakaoOpenApiRestTemplate;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchResponse;
import com.kys.support.JsonUtil;
import com.kys.support.ResourceUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class KakaoOpenApiUrlSearchTest {

    @Mock
    private KakaoOpenApiRestTemplate openApiTemplate;

    private KakaoOpenApiSearch kakaoOpenApiSearch;

    @Before
    public void setUp(){
        kakaoOpenApiSearch = new KakaoOpenApiSearch(openApiTemplate);
    }


    @Test
    public void 상세조회_확인() throws IOException {
        // given
        BookDetailDTO dto = new BookDetailDTO("1157060595");

        given(openApiTemplate.bookSearch(any())).willReturn(makeDetailResponse());

        // when
        DataResponse<BookInfo> response = kakaoOpenApiSearch.bookDetailSearch(dto);

        // then
        assertThat(response.getData()).hasFieldOrPropertyWithValue("isbn", "1157060595");
    }

    @Test(expected = ThirdPartyApiResultNotFoundException.class)
    public void 상세조회_결과없음() throws IOException {
        // given
        BookDetailDTO dto = new BookDetailDTO("1157060595");

        given(openApiTemplate.bookSearch(any())).willReturn(makeDetailResponse());

        // when
        DataResponse<BookInfo> response = kakaoOpenApiSearch.bookDetailSearch(dto);

        // then
    }

    @Test
    public void 쿼리조회() throws IOException {
        // given
        BookDTO dto = BookDTO.builder()
                .search("상실의시대")
                .build();

        given(openApiTemplate.bookSearch(any())).willReturn(makeQueryResponse());

        // when
        PageResponse<BookInfo> response = kakaoOpenApiSearch.bookSearch(dto);

        // then
        assertThat(response.getData().size()).isEqualTo(10);
    }

    @Test
    public void 쿼리조회_결과없음() throws IOException {
        // given
        BookDTO dto = BookDTO.builder()
                .search("상실의시대")
                .build();

        given(openApiTemplate.bookSearch(any())).willReturn(makeQueryNoDataResponse());

        // when
        PageResponse<BookInfo> response = kakaoOpenApiSearch.bookSearch(dto);

        // then
        assertThat(response.getData().size()).isEqualTo(0);
    }

    public ResponseEntity<KakaoBookSearchResponse> makeQueryResponse() throws  IOException {
        return makeTestResponse("classpath:openapi/kakao_book_search.json");
    }

    public ResponseEntity<KakaoBookSearchResponse> makeQueryNoDataResponse() throws  IOException {
        return makeTestResponse("classpath:openapi/kakao_search_nodata.json");
    }

    public ResponseEntity<KakaoBookSearchResponse> makeDetailResponse() throws  IOException {
        return makeTestResponse("classpath:openapi/kakao_detail.json");
    }

    public ResponseEntity<KakaoBookSearchResponse> makeDetailNoDataResponse() throws  IOException {
        return makeTestResponse("classpath:openapi/kakao_detail_nodata.json");
    }

    public ResponseEntity<KakaoBookSearchResponse> makeTestResponse(String path) throws IOException {
        InputStream stream = ResourceUtil.getStream(path);
        KakaoBookSearchResponse response = JsonUtil.jsonToObject(stream, KakaoBookSearchResponse.class);
        return ResponseEntity.ok(response);
    }
}

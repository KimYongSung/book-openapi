package com.kys.openapi.book.service;

import com.kys.openapi.app.exception.ThirdPartyApiResultNotFoundException;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.ListResponse;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.thirdparty.naver.NaverOpenApiRestTemplate;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookSearchResponse;
import com.kys.support.ResourceUtil;
import com.kys.support.XmlUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class NaverOpenApiUrlSearchTest {

    @Mock
    private NaverOpenApiRestTemplate apiTemplate;

    private NaverOpenApiSearch naverOpenApiSearch;

    @Before
    public void setUp(){
        naverOpenApiSearch = new NaverOpenApiSearch(apiTemplate);
    }

    @Test
    public void 쿼리조회() throws JAXBException {
        // given
        BookDTO dto = BookDTO.builder()
                             .query("상실의시대")
                             .build();

        given(apiTemplate.bookSearch(any())).willReturn(makeResponse());

        // when
        ListResponse<BookInfo> response = naverOpenApiSearch.bookSearch(dto);

        // then
        assertThat(response.getData().size()).isEqualTo(10);
    }

    @Test
    public void 쿼리조회_조회결과_없음() throws JAXBException {
        // given
        BookDTO dto = BookDTO.builder()
                             .query("12")
                             .build();

        given(apiTemplate.bookSearch(any())).willReturn(makeNoDataResponse());

        // when
        ListResponse<BookInfo> response = naverOpenApiSearch.bookSearch(dto);

        // then
        assertThat(response.getData().size()).isEqualTo(0);
    }

    @Test
    public void 상세조회_확인() throws JAXBException {
        // given
        BookDTO dto = BookDTO.builder()
                             .isbn("8970123695")
                             .build();

        given(apiTemplate.bookDetailSearch(any())).willReturn(makeDetailResponse());

        // when
        DataResponse<BookInfo> response = naverOpenApiSearch.bookDetailSearch(dto);

        // then
        assertThat(response.getData()).hasFieldOrPropertyWithValue("isbn", "8970123695");
    }

    @Test(expected = ThirdPartyApiResultNotFoundException.class)
    public void 상세조회_조회결과_없음() throws JAXBException {
        // given
        BookDTO dto = BookDTO.builder()
                             .isbn("8970123695")
                             .build();

        given(apiTemplate.bookDetailSearch(any())).willReturn(makeDetailNoDataResponse());

        // when
        DataResponse<BookInfo> response = naverOpenApiSearch.bookDetailSearch(dto);

        // then
    }

    public ResponseEntity<NaverBookSearchResponse> makeResponse() throws JAXBException {
        InputStream stream = ResourceUtil.getStream("classpath:openapi/naver_book_search_10.xml");
        NaverBookSearchResponse response = XmlUtil.xmlToObject(stream, NaverBookSearchResponse.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<NaverBookSearchResponse> makeNoDataResponse() throws JAXBException {
        InputStream stream = ResourceUtil.getStream("classpath:openapi/naver_book_search_nodata.xml");
        NaverBookSearchResponse response = XmlUtil.xmlToObject(stream, NaverBookSearchResponse.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<NaverBookSearchResponse> makeDetailResponse() throws JAXBException {
        InputStream stream = ResourceUtil.getStream("classpath:openapi/naver_detail.xml");
        NaverBookSearchResponse response = XmlUtil.xmlToObject(stream, NaverBookSearchResponse.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<NaverBookSearchResponse> makeDetailNoDataResponse() throws JAXBException {
        InputStream stream = ResourceUtil.getStream("classpath:openapi/naver_detail_no_data.xml");
        NaverBookSearchResponse response = XmlUtil.xmlToObject(stream, NaverBookSearchResponse.class);
        return ResponseEntity.ok(response);
    }
}

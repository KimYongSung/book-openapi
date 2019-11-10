package com.kys.openapi.book.service;

import com.kys.openapi.app.exception.ThirdPartyApiResultFailException;
import com.kys.openapi.app.exception.ThirdPartyApiResultNotFoundException;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.app.util.StringUtil;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookDetailDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.thirdparty.naver.NaverOpenApiRestTemplate;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookDetailSearchRequest;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookItem;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookSearchRequest;
import com.kys.openapi.thirdparty.naver.search.book.NaverBookSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class NaverOpenApiSearch {

    private NaverOpenApiRestTemplate openApiTemplate;

    /**
     * 네이버 OpenAPI 책 검색 요청
     * @param dto
     * @return
     */
    public PageResponse<BookInfo> bookSearch(BookDTO dto) {

        Objects.requireNonNull(dto, "BookDTO is null");

        NaverBookSearchRequest request = NaverBookSearchRequest.builder()
                .query(dto.getSearch())
                .display(dto.getLength())
                .start(dto.getStart())
                                                               .build();

        ResponseEntity<NaverBookSearchResponse> responseEntity = openApiTemplate.bookSearch(request);

        return bookSearchResponseHandler(responseEntity);
    }

    /**
     * 책 검색 결과 처리
     * @param responseEntity
     * @return
     */
    private PageResponse<BookInfo> bookSearchResponseHandler(ResponseEntity<NaverBookSearchResponse> responseEntity) {

        httpStatusCheck(responseEntity);

        NaverBookSearchResponse.Channel channel = responseEntity.getBody().getChannel();

        if(!channel.isItems())
            return PageResponse.success(channel.getTotal(), 0, channel.getStart(), Collections.emptyList());

        List<BookInfo> books = channel.convertItem(this::bookItemToBookInfo);

        return PageResponse.success(channel.getTotal(), channel.getDisplay(), channel.getStart(), books);
    }

    /**
     * 책 상세 검색
     * @param dto
     * @return
     */
    public DataResponse<BookInfo> bookDetailSearch(BookDetailDTO dto) {

        Objects.requireNonNull(dto, "BookDetailDTO is null");

        NaverBookDetailSearchRequest request = NaverBookDetailSearchRequest.builder()
                                                                           .dIsbn(dto.getIsbn())
                                                                           .build();

        ResponseEntity<NaverBookSearchResponse> responseEntity = openApiTemplate.bookDetailSearch(request);

        return bookDetailSearchResponseHandler(responseEntity);
    }

    /**
     * 책 상세검색 결과 처리
     * @param responseEntity
     * @return
     */
    private DataResponse<BookInfo> bookDetailSearchResponseHandler(ResponseEntity<NaverBookSearchResponse> responseEntity) {

        httpStatusCheck(responseEntity);

        NaverBookSearchResponse.Channel channel = responseEntity.getBody().getChannel();

        if(!channel.isItems())
            throw new ThirdPartyApiResultNotFoundException();

        return DataResponse.success(bookItemToBookInfo(channel.getItems().get(0)));
    }

    /**
     * Http 상태 검사
     * @param responseEntity 네이버 api 응답 정보
     */
    private void httpStatusCheck(ResponseEntity<NaverBookSearchResponse> responseEntity) {
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK))
            throw new ThirdPartyApiResultFailException();
    }

    /**
     * 네이버의 BookItem을 BookInfo로 변환
     * @param item
     * @return
     */
    public BookInfo bookItemToBookInfo(NaverBookItem item){

        List<String> isbns = item.getIsbns();

        return BookInfo.builder()
                       .author(item.getAuthor())
                       .contents(item.getDescription())
                       .datetime(item.getPubdate())
                       .isbn(isbns.size() > 0 ? isbns.get(0) : StringUtil.EMPTY)
                       .price(item.getPrice())
                       .publisher(item.getPublisher())
                       .salePrice(item.getDiscount())
                       .thumbnail(item.getImage())
                       .title(item.getTitle())
                       .build();
    }
}

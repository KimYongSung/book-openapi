package com.kys.openapi.book.service;

import com.kys.openapi.app.exception.ThirdPartyApiResultFailException;
import com.kys.openapi.app.exception.ThirdPartyApiResultNotFoundException;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.app.util.StringUtil;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookDetailDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.thirdparty.kakao.KakaoOpenApiRestTemplate;
import com.kys.openapi.thirdparty.kakao.code.BookTarget;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchDocument;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchRequest;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchResponse;
import com.kys.openapi.thirdparty.kakao.search.common.SearchMeta;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class KakaoOpenApiSearch {

    private final KakaoOpenApiRestTemplate openApiTemplate;

    /**
     * 카카오 openAPI 책 검색
     * @param dto
     * @return
     */
    public PageResponse<BookInfo> bookSearch(BookDTO dto) {

        Objects.requireNonNull(dto, "BookDTO is null");

        KakaoBookSearchRequest request = KakaoBookSearchRequest.builder()
                                                               .query(dto.getSearch())
                                                               .page(dto.getStart())
                                                               .size(dto.getLength())
                                                               .build();

        ResponseEntity<KakaoBookSearchResponse> responseEntity = openApiTemplate.bookSearch(request);

        return kakaoBookSearchResponseHandler(request, responseEntity);
    }

    /**
     * 카카오 책 검색 결과 처리
     * @param request
     * @param responseEntity
     * @return
     */
    private PageResponse<BookInfo> kakaoBookSearchResponseHandler(KakaoBookSearchRequest request, ResponseEntity<KakaoBookSearchResponse> responseEntity) {

        httpStatusCheck(responseEntity);

        KakaoBookSearchResponse response = responseEntity.getBody();

        SearchMeta meta = response.getMeta();

        if(!response.isDocument()){
            return PageResponse.success(meta.getTotalCount(), 0, request.getPage(), Collections.emptyList());
        }

        List<BookInfo> books = response.convertDocument(this::documentToBookInfo);

        return PageResponse.success(meta.getTotalCount(), books.size(), request.getPage(), books);
    }

    /**
     * 카카오 책 상세 검색
     * @param dto
     * @return
     */
    public DataResponse<BookInfo> bookDetailSearch(BookDetailDTO dto) {

        Objects.requireNonNull(dto, "BookDetailDTO is null");

        KakaoBookSearchRequest request = KakaoBookSearchRequest.builder()
                                                               .query(dto.getIsbn())
                                                               .target(BookTarget.isbn)
                                                               .build();

        ResponseEntity<KakaoBookSearchResponse> responseEntity = openApiTemplate.bookSearch(request);

        return kakaoBookDetailSearchResponseHandler(responseEntity);
    }

    /**
     * 카카오 책 상세 검색 결과 처리
     * @param responseEntity
     * @return
     */
    private DataResponse<BookInfo> kakaoBookDetailSearchResponseHandler(ResponseEntity<KakaoBookSearchResponse> responseEntity) {

        httpStatusCheck(responseEntity);

        KakaoBookSearchResponse response = responseEntity.getBody();

        if(!response.isDocument()){
            throw new ThirdPartyApiResultNotFoundException();
        }

        return DataResponse.success(documentToBookInfo(response.getDocuments().get(0)));
    }

    private void httpStatusCheck(ResponseEntity<KakaoBookSearchResponse> responseEntity) {
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK))
            throw new ThirdPartyApiResultFailException();
    }

    /**
     * KakaoDocument를 BookInfo로 변환
     * @param document
     * @return
     */
    private BookInfo documentToBookInfo(KakaoBookSearchDocument document){

        List<String> isbns = document.getIsbns();

        return BookInfo.builder()
                       .author(document.getAuthorsByString())
                       .contents(document.getContents())
                       .datetime(document.getDateTime())
                       .isbn(isbns.size() > 0 ? isbns.get(0) : StringUtil.EMPTY)
                       .price(document.getPrice())
                       .publisher(document.getPublisher())
                       .salePrice(document.getSalePrice())
                       .thumbnail(document.getThumbnail())
                       .title(document.getTitle())
                       .build();
    }
}

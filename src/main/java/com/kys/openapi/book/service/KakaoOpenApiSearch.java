package com.kys.openapi.book.service;

import com.kys.openapi.app.exception.ThirdPartyApiResultFailException;
import com.kys.openapi.app.exception.ThirdPartyApiResultNotFoundException;
import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.ListResponse;
import com.kys.openapi.app.util.StringUtil;
import com.kys.openapi.book.dto.BookDTO;
import com.kys.openapi.book.dto.BookInfo;
import com.kys.openapi.thirdparty.kakao.KakaoOpenApiRestTemplate;
import com.kys.openapi.thirdparty.kakao.code.BookTarget;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchDocument;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchRequest;
import com.kys.openapi.thirdparty.kakao.search.book.KakaoBookSearchResponse;
import com.kys.openapi.thirdparty.kakao.search.common.SearchMeta;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class KakaoOpenApiSearch {

    private KakaoOpenApiRestTemplate openApiTemplate;

    /**
     * 카카오 openAPI 책 검색
     * @param dto
     * @return
     */
    public ListResponse<BookInfo> bookSearch(BookDTO dto){

        KakaoBookSearchRequest request = KakaoBookSearchRequest.builder()
                                                               .query(dto.getQuery())
                                                               .page(dto.getPage())
                                                               .size(dto.getDisplay())
                                                               .target(BookTarget.title)
                                                               .build();

        ResponseEntity<KakaoBookSearchResponse> responseEntity = openApiTemplate.bookSearch(request);

        return kakaoBookSearchResponseHandler(dto, responseEntity);
    }

    /**
     * 카카오 책 검색 결과 처리
     * @param dto
     * @param responseEntity
     * @return
     */
    private ListResponse<BookInfo> kakaoBookSearchResponseHandler(BookDTO dto, ResponseEntity<KakaoBookSearchResponse> responseEntity) {

        httpStatusCheck(responseEntity);

        KakaoBookSearchResponse response = responseEntity.getBody();

        SearchMeta meta = response.getMeta();

        if(!response.isDocument()){
            return ListResponse.success(meta.getTotalCount(), 0, dto.getPage(), Collections.emptyList());
        }

        List<BookInfo> books = response.convertDocument(this::documentToBookInfo);

        return ListResponse.success(meta.getTotalCount(), books.size(), dto.getPage(), books);
    }

    /**
     * 카카오 책 상세 검색
     * @param dto
     * @return
     */
    public DataResponse<BookInfo> bookDetailSearch(BookDTO dto){

        KakaoBookSearchRequest request = KakaoBookSearchRequest.builder()
                                                               .query(dto.getIsbn())
                                                               .target(BookTarget.isbn)
                                                               .build();

        ResponseEntity<KakaoBookSearchResponse> responseEntity = openApiTemplate.bookSearch(request);

        return kakaoBookDetailSearchResponseHandler(dto, responseEntity);
    }

    /**
     * 카카오 책 상세 검색 결과 처리
     * @param dto
     * @param responseEntity
     * @return
     */
    private DataResponse<BookInfo> kakaoBookDetailSearchResponseHandler(BookDTO dto, ResponseEntity<KakaoBookSearchResponse> responseEntity) {

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
